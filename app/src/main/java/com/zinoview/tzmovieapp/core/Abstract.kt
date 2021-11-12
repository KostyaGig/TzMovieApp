package com.zinoview.tzmovieapp.core

abstract class Abstract {

    interface Mapper


    interface FactoryMapper<T,R> : Mapper {

        fun map(param: T) : R
    }

    interface Movies {

        fun <T> map(mapper: MoviesMapper<T>) : T
    }


    interface MoviesMapper<T> : Mapper {

        fun map(movies: List<BaseMovie>) : T

        fun map(message: String) : T

        interface TestDataMapper<T> : Mapper {

            fun map(data: String) : T

            fun mapToFailure(message: String) : T

            class DomainMapper : TestDataMapper<DomainLayer> {

                override fun map(data: String): DomainLayer
                    = DomainLayer.Success(data)

                override fun mapToFailure(message: String): DomainLayer
                    = DomainLayer.Failure(message)
            }
        }

        interface Layer {

            fun <T> map(mapper: TestDataMapper<T>) : T
        }

        sealed class DataLayer : Layer {

            data class Success (
                private val data: String
            ) : DataLayer() {

                override fun <T> map(mapper: TestDataMapper<T>): T
                    = mapper.map(data)
            }

            data class Failure(
                private val message: String
            ) : DataLayer() {
                override fun <T> map(mapper: TestDataMapper<T>): T
                        = mapper.mapToFailure(message)
            }
        }

        sealed class DomainLayer : Layer {

            data class Success (
                private val data: String
            ) : DomainLayer() {

                override fun <T> map(mapper: TestDataMapper<T>): T
                        = mapper.map(data)
            }

            data class Failure(
                private val message: String
            ) : DomainLayer() {

                override fun <T> map(mapper: TestDataMapper<T>): T
                        = mapper.mapToFailure(message)
            }
        }

    }


}