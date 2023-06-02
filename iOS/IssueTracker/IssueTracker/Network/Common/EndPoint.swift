//
//  EndPoint.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

enum EndPoint {
    static let issues: URL? = Self.baseURL?.appending(path: Self.Constants.requestIssuesPath)
}

extension EndPoint {
    private static var baseURL: URL? {
        guard let baseURLString = Bundle.main.object(
            forInfoDictionaryKey: Self.Constants.baseURLInfoDictionaryKey
        ) as? String
        else { return nil }
        
        return URL(string: baseURLString)
    }
}

extension EndPoint {
    enum Constants {
        static let requestIssuesPath: String = "/issues"
        static let baseURLInfoDictionaryKey: String = "BaseURL"
    }
}
