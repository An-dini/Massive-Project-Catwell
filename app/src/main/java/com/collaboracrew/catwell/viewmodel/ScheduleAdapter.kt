package com.collaboracrew.catwell.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ListDoneScheduleBinding
import com.collaboracrew.catwell.databinding.ListUpcomingScheduleBinding
import com.collaboracrew.catwell.model.ScheduleModel


class ScheduleAdapter(
    private val scheduleList: List<ScheduleModel>,
    private val clickListener: (ScheduleModel) -> Unit,
    private val isFromPostConsultationFragment: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_DONE = 1
        private const val VIEW_TYPE_POST = 2
    }

    inner class DoneViewHolder(private val binding: ListDoneScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: ScheduleModel) {
            with(binding) {
                ivDoctor.setImageResource(schedule.imageDoctor)
                tvDoctorName.text = schedule.doctorName
                tvVetName.text = schedule.doctorInstance
                tvTime.text = schedule.time
                tvDate.text = schedule.date

                layoutSchedule.setOnClickListener {
                    clickListener(schedule)
                }
            }
        }
    }

    inner class UpcomingViewHolder(private val binding: ListUpcomingScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: ScheduleModel) {
            with(binding) {
                ivDoctor.setImageResource(schedule.imageDoctor)
                tvDoctorName.text = schedule.doctorName
                tvVetName.text = schedule.doctorInstance
                tvTime.text = schedule.time
                tvDate.text = schedule.date

                layoutSchedule.setOnClickListener {
                    clickListener(schedule)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_DONE) {
            val binding = ListDoneScheduleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            DoneViewHolder(binding)
        } else {
            val binding = ListUpcomingScheduleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            UpcomingViewHolder(binding)
        }
    }

    override fun getItemCount(): Int = scheduleList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val schedule = scheduleList[position]

        if (holder is DoneViewHolder && isFromPostConsultationFragment) {
            holder.bind(schedule)
        } else if (holder is UpcomingViewHolder && !isFromPostConsultationFragment) {
            holder.bind(schedule)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isFromPostConsultationFragment) {
            VIEW_TYPE_DONE
        } else {
            VIEW_TYPE_POST
        }
    }
}
