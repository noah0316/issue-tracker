//
//  StoryBoardLiteral.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/25.
//

import Foundation

struct StoryBoardRepresentation {
    let bundle: Bundle?
    let storyboardName: String
    let storyboardId: String
}

enum StoryBoardLiteral {
    static let issue = StoryBoardRepresentation(
        bundle: nil,
        storyboardName: "Issue",
        storyboardId: "IssueListViewController"
    )
}
