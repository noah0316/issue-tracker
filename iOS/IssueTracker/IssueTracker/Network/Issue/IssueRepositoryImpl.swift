//
//  IssueRepositoryImpl.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Combine
import Foundation

struct IssueRepositoryImpl: IssueRepository {
    private let networkRequester: NetworkRequestable
    private let decoder: JSONDecoder
    
    init(networkRequester: NetworkRequestable) {
        self.networkRequester = networkRequester
        self.decoder = JSONDecoder()
        self.setupJSONDecoder()
    }
    
    func requestIssues() -> AnyPublisher<[IssueDTO], IssueRepositoryError> {
        guard let url = EndPoint.issues
        else { return self.makeNetworkBadURLIssueRepositoryError().eraseToAnyPublisher() }
        
        return self.networkRequester.requestData(from: NetworkRequest(url: url))
            .decode(type: [IssueDTO].self, decoder: self.decoder)
            .mapError { error -> IssueRepositoryError in
                return self.convertErrorToIssueRepositoryError(with: error)
            }
            .eraseToAnyPublisher()
    }
}

extension IssueRepositoryImpl {
    private func setupJSONDecoder() {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        self.decoder.dateDecodingStrategy = .formatted(dateFormatter)
    }
    
    private func convertErrorToIssueRepositoryError(with error: Error) -> IssueRepositoryError {
        if error is DecodingError {
            return IssueRepositoryError.decodingError
        } else if let networkServiceError = error as? NetworkServiceError {
            return IssueRepositoryError.networkServiceError(error: networkServiceError)
        } else {
            return IssueRepositoryError.networkServiceError(
                error: NetworkServiceError.httpResponseError(error: .badURLError)
            )
        }
    }
    
    private func makeNetworkBadURLIssueRepositoryError() -> Fail<[IssueDTO], IssueRepositoryError> {
        return Fail(
            error: IssueRepositoryError.networkServiceError(
                error: NetworkServiceError.httpResponseError(error: .badURLError)
            )
        )
    }
}
