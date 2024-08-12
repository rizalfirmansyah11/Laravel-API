package com.rizal.laravelapi.model

data class ResponseListMahasiswa(
	val messaage: String? = null,
	val data: List<DataMahasiswa?>? = null,
	val success: Boolean? = null
)

data class DataMahasiswa(
	val usia: String? = null,
	val image: String? = null,
	val nim: String? = null,
	val gender: String? = null,
	val updatedAt: String? = null,
	val agama: String? = null,
	val idmahasiswa: Int? = null,
	val createdAt: String? = null,
	val namamahasiswa: String? = null,
	val alamat: String? = null
)

