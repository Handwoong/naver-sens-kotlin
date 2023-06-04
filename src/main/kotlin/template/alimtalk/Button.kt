package template.alimtalk

import exception.SensException
import template.alimtalk.ButtonType.*

data class Button(
    val type: ButtonType,
    var name: String,
    val linkMobile: String?,
    val linkPc: String?,
    val schemeIos: String?,
    val schemeAndroid: String?,
) {

    init {
        if (this.type == WL) {
            this.linkPc ?: throw SensException("웹 링크 타입은 linkPc 주소를 입력해야 합니다.")
            this.linkMobile ?: throw SensException("웹 링크 타입은 linkMobile 주소를 입력해야 합니다.")
        }

        if (this.type == AL) {
            this.schemeAndroid ?: throw SensException("앱 링크 타입은 schemeAndroid 주소를 입력해야 합니다.")
            this.schemeIos ?: throw SensException("앱 링크 타입은 schemeIos 주소를 입력해야 합니다.")
        }

        if (this.type == AC) {
            this.name = "채널 추가"
        }
    }

}
