//
//  IssueCollection.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/24.
//

import Foundation

/// Issue 객체를 저장하는 first class collection입니다.
final class IssueCollection {
    private var issues: [Issue]
    
    init(issues: [Issue] = [Issue]()) {
        self.issues = issues
    }
    
    /// collection에 Issue 객체를 저장합니다.
    func append(_ issue: Issue) {
        self.issues.append(issue)
    }
    
    /// collection에 issue 배열을 저장합니다.
    /// - Parameter issues: collection에 저장할 Issue 객체의 배열입니다.
    func append(contentsOf issues: [Issue]) {
        self.issues.append(contentsOf: issues)
    }
    
    /// collection에서 paramter와 일치하는 첫번째 Issue 객체를 제거합니다.
    /// - Parameter issue: collection에서 제거할 Issue입니다.
    func remove(_ issue: Issue) {
        if let index = self.issues.firstIndex(of: issue) {
            self.issues.remove(at: index)
        }
    }
    
    /// - Returns: 컬렉션에 있는 모든 Issue 객체를 반환합니다.
    func allIssues() -> [Issue] {
        return self.issues
    }
}
