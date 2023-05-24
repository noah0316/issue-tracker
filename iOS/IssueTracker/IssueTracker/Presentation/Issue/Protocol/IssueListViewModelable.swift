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
}

protocol IssueListViewModelOutput {
    var issues: AnyPublisher<IssueCollection, Error> { get }
    var issueListLoadingStatus: AnyPublisher<LoadingStatus, Never> { get }
}

protocol IssueListViewModelable {
    var input: IssueListViewModelInput { get }
    var output: IssueListViewModelOutput { get }
}
