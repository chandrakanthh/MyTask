package com.example.mytask.data.models

import com.google.gson.annotations.SerializedName

data class RestCountriesModel(
    @SerializedName("name") var name: Name? = Name(),
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,
    @SerializedName("area") var area: Double? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("flags") var flags: Flags? = Flags(),
)

data class Name(

    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: NativeName? = NativeName()

)

data class NativeName(

    @SerializedName("spa") var spa: Spa? = Spa()

)

data class Spa(

    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null

)

data class Flags(

    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null

)
