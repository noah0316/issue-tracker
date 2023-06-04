//
//  IssueRepositoryError.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/04.
//

import Foundation

enum IssueRepositoryError: Error {
    case networkServiceError(error: NetworkServiceError)
    case decodingError
}
