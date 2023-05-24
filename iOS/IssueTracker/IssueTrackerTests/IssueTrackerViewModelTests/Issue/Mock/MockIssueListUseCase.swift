//
//  MockIssueListUseCase.swift
//  IssueTrackerTests
//
//  Created by Noah on 2023/05/24.
//

import Combine
import Foundation

final class MockIssueListUseCase: IssueListUseCaseType {
    var isFetchIssueListCalled: Bool
    
    init() {
        self.isFetchIssueListCalled = false
    }
    
    func fetchIssueList() -> AnyPublisher<IssueCollection, Error> {
        self.isFetchIssueListCalled = true
        return Future { promise in
            promise(.success(IssueCollection()))
        }.eraseToAnyPublisher()
    }
}

