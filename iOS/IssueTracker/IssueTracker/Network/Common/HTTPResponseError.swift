//
//  HTTPResponseError.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/04.
//

import Foundation

enum HTTPResponseError: Int, Error {
    case badRequest          = 400
    case fobidden            = 403
    case notFound            = 404
    case internalServerError = 500
    case serviceUnAvailable  = 503
    case unknownError
    case badURLError
}

extension HTTPResponseError: LocalizedError {
    var errorDescription: String? {
        switch self {
        case .badRequest:
            return StringLiteral.HTTPResponseError.badRequest
        case .fobidden:
            return StringLiteral.HTTPResponseError.fobidden
        case .notFound:
            return StringLiteral.HTTPResponseError.notFound
        case .internalServerError:
            return StringLiteral.HTTPResponseError.internalServerError
        case .serviceUnAvailable:
            return StringLiteral.HTTPResponseError.serviceUnAvailable
        case .unknownError:
            return StringLiteral.HTTPResponseError.unknownError
        case .badURLError:
            return StringLiteral.HTTPResponseError.badURLError
        }
    }
}
