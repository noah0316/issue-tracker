//
//  LabelDTO.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

struct LabelDTO: Decodable {
    let id: Int
    let labelName: String?
    let backgroundColor: String
    let fontColor: String
}

extension LabelDTO: DomainConvertible {
    func toDomain() -> Label {
        return Label(
            id: self.id,
            name: self.labelName,
            backgroundColor: self.backgroundColor,
            fontColor: self.fontColor
        )
    }
}
