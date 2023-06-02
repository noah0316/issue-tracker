//
//  IssueDTO.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

struct IssueDTO: Decodable {
    let id: Int
    let title: String
    let description: String?
    let createTime: Date
    let updateTime: Date
    let isOpen: Bool
    let milestone: MilestoneDTO
    let author: UserDTO
    let assignees: [UserDTO]
    let labels: [LabelDTO]
}

extension IssueDTO: DomainConvertible {
    // swiftlint:disable:next function_body_length
    func toDomain() -> Issue {
        return Issue(
            id: self.id,
            title: self.title,
            description: self.description,
            createdAt: self.createTime,
            updatedAt: self.updateTime,
            isOpen: self.isOpen,
            milestone: self.milestone.toDomain(),
            assignees: self.assignees.map { $0.toDomain() },
            labels: self.labels.map { $0.toDomain() },
            author: self.author.toDomain()
        )
    }
}
