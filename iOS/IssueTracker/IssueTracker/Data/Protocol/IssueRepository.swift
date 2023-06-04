//
//  IssueRepository.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/02.
//

import Combine
import Foundation

protocol IssueRepository: IssueProviding {
    
}

protocol IssueProviding {
    func requestIssues() -> AnyPublisher<[IssueDTO], IssueRepositoryError>
}
