//
//  NetworkServiceError.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/31.
//

import Foundation

enum NetworkServiceError: Int, Error {
    case badRequest          = 400
    case fobidden            = 403
    case notFound            = 404
    case internalServerError = 500
    case serviceUnAvailable  = 503
    case unknownError
    case urlError
}

extension NetworkServiceError: LocalizedError {
    var errorDescription: String? {
        switch self {
        case .badRequest:
            return StringLiteral.NetworkServiceError.badRequest
        case .fobidden:
            return StringLiteral.NetworkServiceError.fobidden
        case .notFound:
            return StringLiteral.NetworkServiceError.notFound
        case .internalServerError:
            return StringLiteral.NetworkServiceError.internalServerError
        case .serviceUnAvailable:
            return StringLiteral.NetworkServiceError.serviceUnAvailable
        case .unknownError:
            return StringLiteral.NetworkServiceError.unknownError
        case .urlError:
            return StringLiteral.NetworkServiceError.urlError
        }
    }
}
