//
//  IssueUseCaseError.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/04.
//

import Foundation

struct IssueUseCaseError: Error {
    let message: String?
    let errorType: ErrorHandlingType
}

extension IssueUseCaseError {
    enum ErrorHandlingType {
        case showAlert
        case retry
    }
}
