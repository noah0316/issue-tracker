//
//  MockIssueListUseCase.swift
//  IssueTrackerTests
//
//  Created by Noah on 2023/05/24.
//

import Combine
import Foundation

final class MockIssueListUseCase: IssueUseCase {
    var isFetchIssueListCalled: Bool
    
    init() {
        self.isFetchIssueListCalled = false
    }
    
    func fetchIssues() -> AnyPublisher<IssueCollection, Error> {
        self.isFetchIssueListCalled = true
        return Future { promise in
            promise(.success(Self.makeIssue()))
        }.eraseToAnyPublisher()
    }
}

