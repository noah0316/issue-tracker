//
//  IssueListViewModelable.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/24.
//

import Combine
import Foundation

protocol IssueListViewModelInput {
    func viewDidLoad()
    func fetchIssues()
}

protocol IssueListViewModelOutput {
    var issues: AnyPublisher<IssueCollection, Never> { get }
    var issueListLoadingStatus: AnyPublisher<LoadingStatus, Never> { get }
    var alertErrorMessage: AnyPublisher<String, Never> { get }
    var retriableError: AnyPublisher<Void, Never> { get }
}

protocol IssueListViewModelable {
    var input: IssueListViewModelInput { get }
    var output: IssueListViewModelOutput { get }
}
