//
//  NetworkRequestable.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/31.
//

import Combine
import Foundation

protocol NetworkRequestable {
    func requestData(from networkRequest: NetworkRequest) -> AnyPublisher<Data, NetworkServiceError>
}
