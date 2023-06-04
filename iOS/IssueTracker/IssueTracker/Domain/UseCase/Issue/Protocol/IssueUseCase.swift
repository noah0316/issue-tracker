//
//  IssueUseCase.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/24.
//

import Combine
import Foundation

protocol IssueUseCase {
    func requestIssues() -> AnyPublisher<IssueCollection, IssueUseCaseError>
}
