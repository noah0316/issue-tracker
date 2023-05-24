//
//  IssueListViewModel.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/19.
//

import Combine
import Foundation

final class IssueListViewModel: IssueListViewModelable {
    private let useCase: IssueListUseCaseType
    private var cancellables: Set<AnyCancellable>
    private let _issues: PassthroughSubject<IssueCollection, Error>
    private let _issueListLoadingStatus: PassthroughSubject<LoadingStatus, Never>
    
    init(useCase: IssueListUseCaseType) {
        self.useCase = useCase
        self.cancellables = Set<AnyCancellable>()
        self._issues = PassthroughSubject<IssueCollection, Error>()
        self._issueListLoadingStatus = PassthroughSubject<LoadingStatus, Never>()
    }
    
    private func fetchIssueList() {
        self._issueListLoadingStatus.send(.loading)
        self.useCase.fetchIssueList()
            .sink { _ in
                // TODO: 구현예정
            } receiveValue: { [weak self] issues in
                self?._issueListLoadingStatus.send(.done)
                self?._issues.send(issues)
            }
            .store(in: &self.cancellables)
    }
}

extension IssueListViewModel: IssueListViewModelInput {
    var input: IssueListViewModelInput { self }
    
    func viewDidLoad() {
        self.fetchIssueList()
    }
}

extension IssueListViewModel: IssueListViewModelOutput {
    var output: IssueListViewModelOutput { self }
    
    var issues: AnyPublisher<IssueCollection, Error> {
        self._issues.eraseToAnyPublisher()
    }
    
    var issueListLoadingStatus: AnyPublisher<LoadingStatus, Never> {
        self._issueListLoadingStatus.eraseToAnyPublisher()
    }
}
