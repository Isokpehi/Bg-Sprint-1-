package com.example.bgproject.fragments.recruitment

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.bgproject.R
import com.example.bgproject.databinding.FragmentUpdateBinding
import com.example.bgproject.model.Tgl
import com.example.bgproject.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.util.Calendar


class UpdateFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private var datePickerDialog: DatePickerDialog? = null
    private val PICK_IMAGE_REQUEST = 1
    private var selectedState = ""
    private var selectedFileUri: Uri? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(args.currentTgl.govImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivUpdateImage)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.etUpdateFullname.setText(args.currentTgl.fullName)
        binding.etUpdatePhone.setText(args.currentTgl.number)
        binding.tvUpdateSex.setText(args.currentTgl.sex)
        binding.etUpdateDob.setText(args.currentTgl.dob)
        binding.etUpdateBvn.setText(args.currentTgl.bvn)
        binding.etUpdateNin.setText(args.currentTgl.nin)
        binding.tvUpdateState.setText(args.currentTgl.state)
        binding.updateLga.setText(args.currentTgl.lga)
        binding.etUpdateHub.setText(args.currentTgl.hub)
        binding.tvUpdateId.setText(args.currentTgl.govType)
        binding.etUpdateGovId.setText(args.currentTgl.govId)

        val sex = resources.getStringArray(R.array.Sex)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, sex)
        binding.tvUpdateSex.setAdapter(arrayAdapter)

        val state = resources.getStringArray(R.array.State)
        val arrayAdapterState = ArrayAdapter(requireContext(), R.layout.dropdown_item, state)
        binding.tvUpdateState.setAdapter(arrayAdapterState)

        val lga = binding.updateLga
        binding.tvUpdateState.setOnItemClickListener { parent, view, position, id ->
            selectedState = arrayAdapterState.getItem(position).toString()
            binding.updateLga.setText("")
            val lgaAdapter = ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                getLGA(selectedState)
            )

            lga.setAdapter(lgaAdapter)
        }

        val idType = resources.getStringArray(R.array.idType)
        val arrayAdapterIdType = ArrayAdapter(requireContext(), R.layout.dropdown_item, idType)
        binding.tvUpdateId.setAdapter(arrayAdapterIdType)

        val hub = resources.getStringArray(R.array.hub)
        val arrayAdapterHub = ArrayAdapter(requireContext(),R.layout.dropdown_item, hub)
        binding.etUpdateHub.setAdapter(arrayAdapterHub)


        binding.etUpdateDob.setOnClickListener {
            openDatePicker()
        }


        binding.btnUpdate.setOnClickListener {
            updateTgl()
        }

        binding.tvUpdateImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedFileUri = data.data
            binding.ivUpdateImage.setImageURI(selectedFileUri)
            // Use the image URI to load the selected image
        }
    }



    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]

        datePickerDialog = DatePickerDialog(
            requireContext(),
            { View, year, monthOfYear, dayOfMonth ->
                val month = monthOfYear + 1
                var strMonth = "" + month
                var strDayOfMonth = "" + dayOfMonth
                if (month < 10) {
                    strMonth = "0$strMonth"
                }
                if (dayOfMonth < 10) {
                    strDayOfMonth = "0$strDayOfMonth"
                }
                val date = "$strDayOfMonth-$strMonth-$year"
                binding.etUpdateDob.setText(date)
            }, year, month, day
        )
        datePickerDialog!!.setTitle("Select Date")
        datePickerDialog!!.show()
    }



    private fun updateTgl() {
        val fullName = binding.etUpdateFullname.text.toString()
        val number = binding.etUpdatePhone.text.toString()
        val sex = binding.tvUpdateSex.text.toString()
        val dob = binding.etUpdateDob.text.toString()
        val bvn = binding.etUpdateBvn.text.toString()
        val nin = binding.etUpdateNin.text.toString()
        val state = binding.tvUpdateState.text.toString()
        val lga = binding.updateLga.text.toString()
        val hub = binding.etUpdateHub.text.toString()
        val govType = binding.tvUpdateId.text.toString()
        val govId = binding.etUpdateGovId.text.toString()
        val govImage = selectedFileUri.toString()

        when {
            fullName.length < 5 -> Toast.makeText(
                activity,
                "Name must be at least 5 characters long", Toast.LENGTH_LONG
            ).show()

            number.length < 10 -> Toast.makeText(
                activity,
                "Phone number must be at least 10 characters long", Toast.LENGTH_LONG
            ).show()

            sex.isEmpty() -> Toast.makeText(
                activity,
                "Please select a gender", Toast.LENGTH_LONG
            ).show()

            dob.isEmpty() -> Toast.makeText(
                activity,
                "Please enter your date of birth", Toast.LENGTH_LONG
            ).show()

            bvn.length < 11 -> Toast.makeText(
                activity,
                "BVN must be at least 11 characters long", Toast.LENGTH_LONG
            ).show()

            nin.length < 11 -> Toast.makeText(
                activity,
                "NIN must be at least 11 characters long", Toast.LENGTH_LONG
            ).show()

            state.isEmpty() -> Toast.makeText(
                activity,
                "Please select a state", Toast.LENGTH_LONG
            ).show()

            lga.isEmpty() -> Toast.makeText(
                activity,
                "Please enter your LGA", Toast.LENGTH_LONG
            ).show()

            hub.isEmpty() -> Toast.makeText(
                activity,
                "Please enter your hub", Toast.LENGTH_LONG
            ).show()

            govType.isEmpty() -> Toast.makeText(
                activity,
                "Please select an ID type", Toast.LENGTH_LONG
            ).show()

            govId.isEmpty() -> Toast.makeText(
                activity,
                "Please enter your ID number", Toast.LENGTH_LONG
            ).show()

            govImage == null -> Toast.makeText(
                activity,
                "Please select an Image", Toast.LENGTH_LONG
            ).show()
            else -> {
                lifecycleScope.launch {
                    mUserViewModel.tgl = Tgl(
                        args.currentTgl.tglId,
                        fullName = fullName,
                        number = number,
                        sex = sex,
                        dob = dob,
                        bvn = bvn,
                        nin = nin,
                        state = state,
                        lga = lga,
                        hub = hub,
                        govType = govType,
                        govId = govId,
                        govImage = govImage,
                        officerId = "",
                        testFlag = 0
                    )
                    val sharedPreferences =
                        requireActivity().getSharedPreferences(
                            "my_preferences",
                            Context.MODE_PRIVATE
                        )
                    mUserViewModel.tgl?.officerId =
                        sharedPreferences.getString("OFFICER_ID", "").toString()
                    mUserViewModel.updateTgl()
                    Toast.makeText(
                        context,
                        "Successfully Updated",
                        Toast.LENGTH_SHORT
                    ).show()
                    mUserViewModel.tgl = null
                    findNavController().navigate(R.id.action_updateFragment_to_tglsFragment)
                }
            }


        }
    }



    private fun getLGA(state: String): Array<String> {
        return when (state) {
            getString(R.string.Abia) -> resources.getStringArray(R.array.Abia)
            getString(R.string.Adamawa) -> resources.getStringArray(R.array.Adamawa)
            getString(R.string.Akwa) -> resources.getStringArray(R.array.Akwa_Ibom)
            getString(R.string.Anambra) -> resources.getStringArray(R.array.Anambra)
            getString(R.string.Bauchi) -> resources.getStringArray(R.array.Bauchi)
            getString(R.string.Bayelsa) -> resources.getStringArray(R.array.Bayelsa)
            getString(R.string.Benue) -> resources.getStringArray(R.array.Benue)
            getString(R.string.Borno) -> resources.getStringArray(R.array.Borno)
            getString(R.string.Cross_River) -> resources.getStringArray(R.array.Cross_River)
            getString(R.string.Delta) -> resources.getStringArray(R.array.Delta)
            getString(R.string.Ebonyi) -> resources.getStringArray(R.array.Ebonyi)
            getString(R.string.Edo) -> resources.getStringArray(R.array.Edo)
            getString(R.string.Ekiti) -> resources.getStringArray(R.array.Ekiti)
            getString(R.string.Enugu) -> resources.getStringArray(R.array.Enugu)
            getString(R.string.Gombe) -> resources.getStringArray(R.array.Gombe)
            getString(R.string.Imo) -> resources.getStringArray(R.array.Imo)
            getString(R.string.Jigawa) -> resources.getStringArray(R.array.Jigawa)
            getString(R.string.Kaduna) -> resources.getStringArray(R.array.Kaduna)
            getString(R.string.Kano) -> resources.getStringArray(R.array.Kano)
            getString(R.string.Katsina) -> resources.getStringArray(R.array.Katsina)
            getString(R.string.Kebbi) -> resources.getStringArray(R.array.Kebbi)
            getString(R.string.Kogi) -> resources.getStringArray(R.array.Kogi)
            getString(R.string.Kwara) -> resources.getStringArray(R.array.Kwara)
            getString(R.string.Lagos) -> resources.getStringArray(R.array.Lagos)
            getString(R.string.Nasarawa) -> resources.getStringArray(R.array.Nasarawa)
            getString(R.string.Niger) -> resources.getStringArray(R.array.Niger)
            getString(R.string.Ogun) -> resources.getStringArray(R.array.Ogun)
            getString(R.string.Ondo) -> resources.getStringArray(R.array.Ondo)
            getString(R.string.Osun) -> resources.getStringArray(R.array.Osun)
            getString(R.string.Oyo) -> resources.getStringArray(R.array.Oyo)
            getString(R.string.Plateau) -> resources.getStringArray(R.array.Plateau)
            getString(R.string.Rivers) -> resources.getStringArray(R.array.Rivers)
            getString(R.string.Sokoto) -> resources.getStringArray(R.array.Sokoto)
            getString(R.string.Taraba) -> resources.getStringArray(R.array.Taraba)
            getString(R.string.Yobe) -> resources.getStringArray(R.array.Yobe)
            getString(R.string.Zamfara) -> resources.getStringArray(R.array.Zamfara)
            else -> emptyArray()
        }
    }

}
