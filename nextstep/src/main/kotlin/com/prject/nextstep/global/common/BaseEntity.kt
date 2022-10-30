package com.prject.nextstep.global.common

import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import com.fasterxml.uuid.Generators
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    open val id: UUID = Generators.timeBasedGenerator().generate()
}