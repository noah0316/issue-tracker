//
//  IssueListUseCaseType.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/24.
//

import Combine
import Foundation

protocol IssueListUseCaseType {
    func fetchIssueList() -> AnyPublisher<IssueCollection, Error>
}
