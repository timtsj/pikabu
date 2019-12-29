package com.tsdreamdeveloper.app.domain.usecase.base

import io.reactivex.Single

abstract class SingleUseCase<in PARAMS, RESULT> {

    fun execute(parameters: PARAMS) = build(parameters)

    protected abstract fun build(parameters: PARAMS): Single<RESULT>
}

