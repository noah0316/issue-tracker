//
//  Label.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

struct Label: Hashable {
    let id: Int
    let name: String?
    let backgroundColor: String
    let fontColor: String
}
