//
//  IssueListViewModel.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/19.
//

import Combine
import Foundation

final class IssueListViewModel: IssueListViewModelable {
    private let useCase: IssueUseCase
    private var cancellables: Set<AnyCancellable>
    private let _issues: PassthroughSubject<IssueCollection, Never>
    private let _issueListLoadingStatus: PassthroughSubject<LoadingStatus, Never>
    private let _alertErrorMessage: PassthroughSubject<String, Never>
    private let _retriableError: PassthroughSubject<Void, Never>
    
    init(useCase: IssueUseCase) {
        self.useCase = useCase
        self.cancellables = Set<AnyCancellable>()
        self._issues = PassthroughSubject<IssueCollection, Never>()
        self._issueListLoadingStatus = PassthroughSubject<LoadingStatus, Never>()
        self._alertErrorMessage = PassthroughSubject<String, Never>()
        self._retriableError = PassthroughSubject<Void, Never>()
    }
}

extension IssueListViewModel: IssueListViewModelInput {
    var input: IssueListViewModelInput { self }
    
    func viewDidLoad() {
        self.fetchIssues()
    }
    
    // swiftlint:disable:next function_body_length
    func fetchIssues() {
        self._issueListLoadingStatus.send(.loading)
        self.useCase.requestIssues()
            .mapError { issueUseCaseError -> Error in
                self.handleIssueUseCaseError(with: issueUseCaseError)
                return issueUseCaseError
            }
            .sink { [weak self] _ in
                self?._issueListLoadingStatus.send(.done)
            } receiveValue: { [weak self] issues in
                self?._issues.send(issues)
            }
            .store(in: &self.cancellables)
    }
}

extension IssueListViewModel: IssueListViewModelOutput {
    var output: IssueListViewModelOutput { self }
    
    var issues: AnyPublisher<IssueCollection, Never> {
        return self._issues.eraseToAnyPublisher()
    }
    
    var issueListLoadingStatus: AnyPublisher<LoadingStatus, Never> {
        return self._issueListLoadingStatus.eraseToAnyPublisher()
    }
    
    var alertErrorMessage: AnyPublisher<String, Never> {
        return self._alertErrorMessage.eraseToAnyPublisher()
    }
    
    var retriableError: AnyPublisher<Void, Never> {
        return self._retriableError.eraseToAnyPublisher()
    }
}

extension IssueListViewModel {
    private func handleIssueUseCaseError(with error: IssueUseCaseError) {
        switch error.errorType {
        case .showAlert:
            self._alertErrorMessage.send(error.message ?? StringLiteral.emptyString)
        case .retry:
            self._retriableError.send()
        }
    }
}
