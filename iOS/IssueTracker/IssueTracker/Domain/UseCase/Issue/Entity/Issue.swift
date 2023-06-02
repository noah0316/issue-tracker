//
//  Issue.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/19.
//

import Foundation

struct Issue: Identifiable, Hashable {
    let id: Int
    let title: String
    let description: String?
    let createdAt: Date
    let updatedAt: Date
    let isOpen: Bool
    let milestone: Milestone
    let assignees: [User]?
    let labels: [Label]?
    let author: User
}
