//
//  StringLiteral.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/25.
//

import Foundation

enum StringLiteral {
    enum IssueListViewController {
        static let title = "이슈"
    }
    
    enum NetworkServiceError {
        static let badRequest = "잘못된 요청입니다."
        static let fobidden = "권한이 없습니다."
        static let notFound = "콘텐츠를 찾을 수 없습니다."
        static let internalServerError = "서버 에러"
        static let serviceUnAvailable = "서비스를 사용할 수 없습니다."
        static let unknownError = "알 수 없는 에러"
        static let urlError = "잘못된 URL입니다."
    }
    
    enum ErrorAlertController {
        static let title = "네트워크 에러"
    }
}
