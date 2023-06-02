//
//  UserDTO.swift
//  IssueTracker
//
//  Created by Noah on 2023/06/01.
//

import Foundation

struct UserDTO: Decodable {
    let id: Int
    let name: String
    let profileURL: String?
}

extension UserDTO {
    enum CodingKeys: String, CodingKey {
        case id, name
        case profileURL = "profileUrl"
    }
}

extension UserDTO: DomainConvertible {
    func toDomain() -> User {
        let profileURL = self.profileURL.flatMap { URL(string: $0) }
        
        return User(
            id: self.id,
            name: self.name,
            profileURL: profileURL
        )
    }
}
