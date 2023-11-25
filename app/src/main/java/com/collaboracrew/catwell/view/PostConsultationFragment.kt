package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentPostConsultationBinding
import com.collaboracrew.catwell.model.SCHEDULE_ID_EXTRA
import com.collaboracrew.catwell.model.ScheduleModel

class PostConsultationFragment : Fragment() {
    private lateinit var binding: FragmentPostConsultationBinding
    private lateinit var adapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val scheduleList = generateScheduleList()
        adapter = ScheduleAdapter(scheduleList, clickListener = {
            val intent = Intent(requireContext(), Detail_Vet_Puskeswan::class.java)
            startActivity(intent)
                                                                }, isFromPostConsultationFragment = true)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }


    private fun generateScheduleList(): List<ScheduleModel> {
        val scheduleList = mutableListOf<ScheduleModel>()

        scheduleList.add(ScheduleModel(R.drawable.aji, "03 Jul", "13:00 - 14:00", "Drh. Aji", "OJ Pet Care", 1))
        scheduleList.add(ScheduleModel(R.drawable.mutiara, "03 Augst", "13:00 - 14:00", "Drh. Mutiara", "OJ Pet Care", 2))
        scheduleList.add(ScheduleModel(R.drawable.chandra, "03 Sept", "13:00 - 14:00", "Drh. Chandra", "OJ Pet Care", 3))
        scheduleList.add(ScheduleModel(R.drawable.annisa, "03 Oct", "13:00 - 14:00", "Drh. Annisa", "OJ Pet Care", 4))

        return scheduleList
    }
}