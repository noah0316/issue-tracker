//
//  NetworkServiceError.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/31.
//

import Foundation

enum NetworkServiceError: Error {
    case httpResponseError(error: HTTPResponseError)
    case urlError(error: URLError)
}
