package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.PaymentMethodModel

class PaymentMethodsAdapter(private val paymentMethods: List<PaymentMethodModel>) :
    RecyclerView.Adapter<PaymentMethodsAdapter.PaymentMethodViewHolder>() {

    private var showAllLogos = false
    private var isExpanded = false
    private var paymentNameTextView: TextView? = null
    private var lastClickedPosition = RecyclerView.NO_POSITION

    private val displayedItemCount: Int
        get() = if (isExpanded) paymentMethods.size else 5

    class PaymentMethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val paymentMethodLogo: ImageView = itemView.findViewById(R.id.paymentMethodLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.payment_list_mitra, parent, false)
        return PaymentMethodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val currentItem = paymentMethods[position]

        if (currentItem.isVisible || showAllLogos) {
            holder.paymentMethodLogo.visibility = View.VISIBLE
        } else {
            holder.paymentMethodLogo.visibility = View.GONE
        }

        holder.paymentMethodLogo.setImageResource(currentItem.logoResource)

        holder.itemView.setOnClickListener {
            val adapterPosition = holder.adapterPosition

            if (currentItem.isVisible) {
                val mitraNameResource = when (currentItem.logoResource) {
                    R.drawable.ic_dana_logo -> R.string.dana
                    R.drawable.ic_ovo_logo -> R.string.ovo
                    R.drawable.ic_gopay_logo -> R.string.gopay
                    R.drawable.ic_shopeepay_logo -> R.string.shopeepay
                    R.drawable.ic_linkaja_logo -> R.string.linkaja
                    R.drawable.ic_mandiri_logo -> R.string.mandiri
                    R.drawable.ic_bca_logo -> R.string.bca
                    R.drawable.ic_bri_logo -> R.string.bri
                    R.drawable.ic_bni_logo -> R.string.bni
                    else -> R.string.dana
                }

                val mitraName = holder.itemView.context.getString(mitraNameResource)
                paymentNameTextView?.text = mitraName
                paymentNameTextView?.visibility = View.VISIBLE

                val scaleAnimation =
                    AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_up)
                holder.paymentMethodLogo.startAnimation(scaleAnimation)
            }
        }
    }

    override fun getItemCount(): Int {
        return displayedItemCount
    }

    // Method to toggle visibility of items when "lihat selengkapnya" is clicked
    fun expandList() {
        isExpanded = !isExpanded
        notifyDataSetChanged()
    }

    fun setPaymentNameTextView(textView: TextView) {
        paymentNameTextView = textView
    }
}
