//
//  User.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/19.
//

import Foundation

struct User: Identifiable, Hashable {
    let id: Int
    let name: String
    let profileURL: URL?
}
