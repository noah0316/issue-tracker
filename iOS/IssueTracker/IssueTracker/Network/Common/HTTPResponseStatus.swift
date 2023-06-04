//
//  HTTPResponseStatus.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/31.
//

import Foundation

struct HTTPResponseStatus {
    private let httpURLResponse: HTTPURLResponse
    private let successStatusRange = (200 ... 299)
    
    init?(urlResponse: URLResponse) {
        guard let httpURLResponse = urlResponse as? HTTPURLResponse
        else { return nil }
        
        self.httpURLResponse = httpURLResponse
    }
    
    func isSuccessful() -> Bool {
        return self.successStatusRange.contains(self.httpURLResponse.statusCode)
    }
    
    func makeNetworkServiceError() -> NetworkServiceError {
        guard let httpResponseError = HTTPResponseError(rawValue: self.httpURLResponse.statusCode)
        else {
            return NetworkServiceError.httpResponseError(error: .unknownError)
        }
        
        return NetworkServiceError.httpResponseError(
            error: httpResponseError
        )
    }
}
