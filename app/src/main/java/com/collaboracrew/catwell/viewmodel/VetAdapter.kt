import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.collaboracrew.catwell.databinding.ListVetBinding
import com.collaboracrew.catwell.model.Vet_Model

class VetAdapter(
    private val vetList: ArrayList<Vet_Model.Result>,
    private val clickListener: (Vet_Model.Result) -> Unit
) : RecyclerView.Adapter<VetAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListVetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Vet_Model.Result) {
            with(binding) {
                namaVet.text = result.Nama_Vet
                Waktu.text = result.Waktu_Buka
                deskripsiVet.text = result.Alamat

                Glide.with(binding.root)
                    .load(result.Img_Vet)
                    .into(imgVet)

                binding.layoutVet.setOnClickListener {
                    clickListener(result)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListVetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = vetList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vetList[position])
    }

    fun setData(newList: ArrayList<Vet_Model.Result>) {
        vetList.clear()
        vetList.addAll(newList)
        notifyDataSetChanged()
    }
}
