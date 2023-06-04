//
//  NetworkService.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/31.
//

import Combine
import Foundation

struct NetworkService: NetworkRequestable {
    private let session: URLSession
    
    init(session: URLSession) {
        self.session = session
    }
    
    // swiftlint:disable function_body_length
    /// Web Service의 end point 호출을 할 때 사용합니다.
    /// - note: 공식문서에서는 remote server 와의 소규모 인터렉션(Web service endpoint 호출과 같은)용도에 URLSessionDataTask를 사용하는 것이 이상적이라 하여
    /// 이를 근거로 URLSessionDataTask를 사용하였습니다.
    ///
    /// - seealso: [Apple Developer Documentation: URL Loading System](https://developer.apple.com/documentation/foundation/url_loading_system/fetching_website_data_into_memory#overview)
    func requestData(from networkRequest: NetworkRequest) -> AnyPublisher<Data, NetworkServiceError> {
        return self.session.dataTaskPublisher(for: networkRequest.buildURLRequest())
            .tryMap { (data: Data, response: URLResponse) in
                guard let httpResponseStatus = HTTPResponseStatus(urlResponse: response)
                else { throw NetworkServiceError.urlError(error: .init(.badServerResponse)) }
                
                if httpResponseStatus.isSuccessful() {
                    return data
                }
                
                throw httpResponseStatus.makeNetworkServiceError()
            }
            .mapError { error -> NetworkServiceError in
                return self.convertErrorToNetworkServiceError(with: error)
            }
            .eraseToAnyPublisher()
    }
    // swiftlint:enable function_body_length
}

extension NetworkService {
    private func convertErrorToNetworkServiceError(with error: Error) -> NetworkServiceError {
        if let networkError = error as? NetworkServiceError {
            return networkError
        } else if let urlError = error as? URLError {
            return NetworkServiceError.urlError(error: urlError)
        } else {
            return NetworkServiceError.httpResponseError(error: .unknownError)
        }
    }
}
