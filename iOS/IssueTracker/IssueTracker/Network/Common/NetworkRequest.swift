//
//  NetworkRequest.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/31.
//

import Foundation

struct NetworkRequest {
    private let url: URL
    private let headers: [String: String]?
    private let body: Data?
    private let requestTimeOutInterval: Double
    private let httpMethod: HTTPMethod
    
    init(
        url: URL,
        headers: [String: String]? = nil,
        body: Data? = nil,
        requestTimeOutInterval: Double = 5.0,
        httpMethod: HTTPMethod = .get
    ) {
        self.url = url
        self.headers = headers
        self.body = body
        self.requestTimeOutInterval = requestTimeOutInterval
        self.httpMethod = httpMethod
    }
    
    func buildURLRequest() -> URLRequest {
        var urlRequest = URLRequest(url: self.url)
        urlRequest.httpMethod = self.httpMethod.rawValue
        urlRequest.timeoutInterval = self.requestTimeOutInterval
        urlRequest.allHTTPHeaderFields = self.headers ?? [:]
        urlRequest.httpBody = self.body
        return urlRequest
    }
}

extension NetworkRequest {
    enum HTTPMethod: String {
        case get = "GET"
        case post = "POST"
        case put = "PUT"
        case delete = "DELETE"
    }
}
