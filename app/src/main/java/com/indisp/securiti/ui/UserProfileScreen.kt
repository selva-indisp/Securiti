import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.indisp.securiti.data.repository.UserRepositoryImpl
import com.indisp.securiti.design_system.components.ButtonCta
import com.indisp.securiti.design_system.components.DropDownField
import com.indisp.securiti.design_system.components.DropDownItem
import com.indisp.securiti.design_system.components.InputField
import com.indisp.securiti.design_system.components.RadioGroup
import com.indisp.securiti.design_system.components.RadioItem
import com.indisp.securiti.design_system.components.ToolBar
import com.indisp.securiti.domain.model.AgeGroup
import com.indisp.securiti.domain.model.Gender
import com.indisp.securiti.domain.usecase.RetrieveSavedUserProfileUseCase
import com.indisp.securiti.domain.usecase.SaveUserProfileUseCase
import com.indisp.securiti.storage.Storage
import com.indisp.securiti.ui.UserProfileViewModel
import com.indisp.securiti.ui.UserProfileViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen() {
    val context = LocalContext.current
    val userRepository = remember {
        UserRepositoryImpl(Storage(context))
    }
    val userProfileViewModel: UserProfileViewModel = viewModel(
        factory = UserProfileViewModelFactory(
            saveUserProfileUseCase = SaveUserProfileUseCase(userRepository),
            retrieveSavedUserProfileUseCase = RetrieveSavedUserProfileUseCase(userRepository)
        )
    )
    val state by userProfileViewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            ToolBar(
                title = "Create Profile",
                actionBuilder = {
                    ButtonCta(
                        text = "Save",
                        onClick = userProfileViewModel::onSubmitClicked
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            NameTextField(
                value = state.selectedName,
                onValueChange = userProfileViewModel::onNameChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            GenderField(
                selectedGender = state.selectedGender,
                options = state.genders,
                onValueChange = userProfileViewModel::onGenderSelected
            )
            Spacer(modifier = Modifier.height(16.dp))
            AgeGroupDropdown(
                selectedAgeGroup = state.selectedAgeGroup,
                ageGroups = state.ageGroups,
                onAgeGroupSelected = userProfileViewModel::onAgeGroupSelected
            )
        }
    }
}

@Composable
private fun NameTextField(value: String, onValueChange: (String) -> Unit) {
    InputField(
        value = value,
        label = "Name",
        placeHolder = "Enter your name",
        onValueChange = onValueChange
    )
}

@Composable
private fun GenderField(
    selectedGender: Gender?,
    options: List<Gender>,
    onValueChange: (Gender) -> Unit
) {
    val genderOptions = remember(options) { options.map { RadioItem(it.genderType, it) } }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Gender", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(16.dp))
        RadioGroup(
            selectedOption = selectedGender,
            options = genderOptions,
            onOptionSelected = onValueChange
        )
    }
}

@Composable
private fun AgeGroupDropdown(
    ageGroups: List<AgeGroup>,
    selectedAgeGroup: AgeGroup?,
    onAgeGroupSelected: (AgeGroup) -> Unit
) {
    val selectedValue = selectedAgeGroup?.groupName ?: "Select Age Group"
    val options = remember(ageGroups) { ageGroups.map { DropDownItem(it.groupName, it) } }
    DropDownField(
        selectedValue = selectedValue,
        options = options,
        onOptionSelected = onAgeGroupSelected
    )
}




