package id.ac.unand.uas_ptb_2007.models

import com.google.gson.annotations.SerializedName

data class ListLogBookResponse(

    @field:SerializedName("report_title")
    val reportTitle: String? = null,

    @field:SerializedName("end_at")
    val endAt: Any? = null,

    @field:SerializedName("link_seminar")
    val linkSeminar: Any? = null,

    @field:SerializedName("proposal_id")
    val proposalId: Int? = null,

    @field:SerializedName("attendees_list")
    val attendeesList: Any? = null,

    @field:SerializedName("supervisor_id")
    val supervisorId: Any? = null,

    @field:SerializedName("news_event")
    val newsEvent: String? = null,

    @field:SerializedName("seminar_room_id")
    val seminarRoomId: Int? = null,

    @field:SerializedName("certificate")
    val certificate: Any? = null,

    @field:SerializedName("logbooks")
    val logbooks: List<LogbooksItem?>? = null,

    @field:SerializedName("student_id")
    val studentId: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: Any? = null,

    @field:SerializedName("start_at")
    val startAt: Any? = null,

    @field:SerializedName("cancellation_reason")
    val cancellationReason: String? = null,

    @field:SerializedName("internship_score")
    val internshipScore: Any? = null,

    @field:SerializedName("seminar_date")
    val seminarDate: String? = null,

    @field:SerializedName("report_receipt")
    val reportReceipt: Any? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("grade")
    val grade: String? = null,

    @field:SerializedName("work_report")
    val workReport: Any? = null,

    @field:SerializedName("seminar_deadline")
    val seminarDeadline: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("activity_report")
    val activityReport: Any? = null,

    @field:SerializedName("status")
    val status: Int? = null
)