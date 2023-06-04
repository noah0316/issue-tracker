//
//  IssueUseCaseImpl.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/02.
//

import Combine
import Foundation

struct IssueUseCaseImpl: IssueUseCase {
    private let issueRepository: IssueRepository
    
    init(issueRepository: IssueRepository) {
        self.issueRepository = issueRepository
    }
    
    func requestIssues() -> AnyPublisher<IssueCollection, IssueUseCaseError> {
        return self.issueRepository.requestIssues()
            .map { $0.map { $0.toDomain() } }
            .map { IssueCollection(issues: $0) }
            .mapError { error -> IssueUseCaseError  in
                return self.convertErrorToIssueError(with: error)
            }
            .eraseToAnyPublisher()
    }
}

extension IssueUseCaseImpl {
    private func convertErrorToIssueError(with error: Error) -> IssueUseCaseError {
        if let issueRepositoryError = error as? IssueRepositoryError {
            switch issueRepositoryError {
            case .networkServiceError(let networkServiceError):
                return self.convertNetworkServiceErrorToIssueError(with: networkServiceError)
            case .decodingError:
                return self.makeDecodingIssueError()
            }
        }
        
        return self.makeUnknownIssueError()
    }
    
    private func convertNetworkServiceErrorToIssueError(with error: NetworkServiceError) -> IssueUseCaseError {
        switch error {
        case .httpResponseError(let httpResponseError):
            return self.convertHTTPResponseErrorToIssueError(with: httpResponseError)
        case .urlError(let urlError):
            return self.convertURLErrorToIssueUseCaseError(with: urlError)
        }
    }
    
    private func convertURLErrorToIssueUseCaseError(with urlError: URLError) -> IssueUseCaseError {
        return IssueUseCaseError(
            message: urlError.localizedDescription,
            errorType: .retry
        )
    }
    
    private func convertHTTPResponseErrorToIssueError(with httpResponseError: HTTPResponseError) -> IssueUseCaseError {
        return IssueUseCaseError(
            message: httpResponseError.localizedDescription,
            errorType: .showAlert
        )
    }
    
    private func makeDecodingIssueError() -> IssueUseCaseError {
        return IssueUseCaseError(
            message: StringLiteral.IssueRepositoryError.decoding,
            errorType: .showAlert
        )
    }
    
    private func makeUnknownIssueError() -> IssueUseCaseError {
        return IssueUseCaseError(
            message: StringLiteral.IssueUseCaseError.unknown,
            errorType: .showAlert
        )
    }
}
