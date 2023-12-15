package com.example.reservation_presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.convertToText
import com.example.navigation.editTextIsEmpty
import com.example.navigation.setupTextWatcherDate
import com.example.navigation.setupTextWatcherNames
import com.example.navigation.setupTextWatcherPassportNumber
import com.example.navigation.showToast
import com.example.reservation_presenter.R
import com.example.reservation_presenter.databinding.ItemTouristsBinding

private const val MAX_TOURISTS = 5

class TouristAdapter(
    private val buyerInformationCheck: () -> Unit,
    private val btnPay: Button,
    private val navController: NavController,
    private val context: Context
) : ListAdapter<String, TouristAdapter.TouristViewHolder>(TouristDiffCallback()) {

    val countries = arrayOf(
        "Россия",
        "США",
        "Канада",
        "Германия",
        "Франция",
        "Италия",
        "Япония",
        "Китай",
        "Бразилия",
        "Индия"
    )

    init {
        submitList(
            listOf(
                context.getString(
                    R.string.counts_tourists, context.convertToText(currentList.size + 1)
                )
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTouristsBinding.inflate(inflater, parent, false)
        return TouristViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun addItem() {
        if (currentList.size < MAX_TOURISTS) {
            val newList = currentList.toMutableList()
            newList.add(
                context.getString(
                    R.string.counts_tourists, context.convertToText(newList.size + 1)
                )
            )
            submitList(newList)
        }
    }


    fun isMaxTouristsReached(): Boolean {
        return currentList.size >= 4
    }

    inner class TouristViewHolder(private val binding: ItemTouristsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(touristCount: String) {

            binding.tvTouristCount.text = touristCount

            binding.llEd.visibility = View.GONE
            binding.ibInfoTourist.setImageResource(R.drawable.ic_more_info_tourist)

            binding.clTourist.setOnClickListener {
                binding.llEd.visibility =
                    if (binding.llEd.visibility == View.VISIBLE) View.GONE else View.VISIBLE

                val imageResource =
                    if (binding.llEd.visibility == View.VISIBLE) R.drawable.ic_hide_info_tourist
                    else R.drawable.ic_more_info_tourist

                binding.ibInfoTourist.setImageResource(imageResource)
            }
            setupTextWatcherNames(binding.edName, binding.edNameLayout)
            setupTextWatcherNames(binding.edSureName, binding.edSureNameLayout)
            setupTextWatcherNames(binding.edCitizenship, binding.edCitizenshipLayout)
            setupTextWatcherPassportNumber(
                binding.edPassportNumber,
                binding.edPassportNumberLayout,
                "№",
                context.getString(R.string.incorrect_date_birth)
            )
            setupTextWatcherDate(
                binding.edPeriodPassport,
                binding.edPeriodPassportLayout,
                ".",
                context.getString(R.string.incorrect_date_birth)
            )
            setupTextWatcherDate(
                binding.edDateBirth,
                binding.edDateBirthLayout,
                ".",
                context.getString(R.string.incorrect_date_birth)
            )

            val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, countries)
            binding.edCitizenship.setAdapter(adapter)

            binding.edCitizenship.setOnItemClickListener { parent, _, position, _ ->
                val selectedCountry = parent.getItemAtPosition(position).toString()
                binding.edCitizenship.setText(selectedCountry)
                binding.edCitizenship.setSelection(binding.edCitizenship.text.length)
            }

            binding.apply {
                btnPay.setOnClickListener {
                    buyerInformationCheck()
                    if (edNameLayout.error != null
                        || edSureNameLayout.error != null
                        || edDateBirthLayout.error != null
                        || edPassportNumberLayout.error != null
                        || edPeriodPassportLayout.error != null
                        || edCitizenshipLayout.error != null
                    ) {
                        context.showToast(context.getString(R.string.please_fill_out_all_fields_correctly))
                    } else if (
                        edName.text.toString().isEmpty()
                        || edSureName.text.toString().isEmpty()
                        || edDateBirth.text.toString().isEmpty()
                        || edPassportNumber.text.toString().isEmpty()
                        || edPeriodPassport.text.toString().isEmpty()
                        || edCitizenship.text.toString().isEmpty()
                    ) {
                        binding.llEd.visibility = View.VISIBLE
                        binding.ibInfoTourist.setImageResource(R.drawable.ic_hide_info_tourist)

                        context.showToast(context.getString(R.string.fields_cannot_be_empty))
                        editTextIsEmpty(
                            edSureName,
                            edSureNameLayout,
                            context.getString(R.string.fields_cannot_be_empty)
                        )
                        editTextIsEmpty(
                            edName,
                            edNameLayout,
                            context.getString(R.string.fields_cannot_be_empty)
                        )
                        editTextIsEmpty(
                            edDateBirth,
                            edDateBirthLayout,
                            context.getString(R.string.fields_cannot_be_empty)
                        )
                        editTextIsEmpty(
                            edPassportNumber,
                            edPassportNumberLayout,
                            context.getString(R.string.fields_cannot_be_empty)
                        )
                        editTextIsEmpty(
                            edPeriodPassport,
                            edPeriodPassportLayout,
                            context.getString(R.string.fields_cannot_be_empty)
                        )
                        editTextIsEmpty(
                            edCitizenship,
                            binding.edCitizenshipLayout,
                            context.getString(R.string.fields_cannot_be_empty)
                        )
                    } else {
                        binding.llEd.visibility = View.GONE
                        binding.ibInfoTourist.setImageResource(R.drawable.ic_more_info_tourist)
                        edNameLayout.error = null
                        edSureNameLayout.error = null
                        edSureNameLayout.isErrorEnabled = false
                        edNameLayout.isErrorEnabled = false
                        edDateBirthLayout.isErrorEnabled = false
                        edDateBirthLayout.error = null
                        edPassportNumberLayout.isErrorEnabled = false
                        edPassportNumberLayout.error = null
                        edPeriodPassportLayout.isErrorEnabled = false
                        edPeriodPassportLayout.error = null
                        edCitizenshipLayout.isErrorEnabled = false
                        edCitizenshipLayout.error = null
                        /*navController.navigate(R.id.paidFragment)*/
                    }
                }
            }

        }
    }

    class TouristDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}

