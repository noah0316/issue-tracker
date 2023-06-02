//
//  MilestoneDTO.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

struct MilestoneDTO: Decodable {
    let id: Int
    let title: String
}

extension MilestoneDTO: DomainConvertible {
    func toDomain() -> Milestone {
        return Milestone(
            id: self.id,
            title: self.title
        )
    }
}
