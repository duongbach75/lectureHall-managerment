import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { Amphitheater } from './amphitheater';
import { Constants } from '../../common/util/constants';

/**
 * @description: Define the country form. Use for enter data of the country object
 */
export class AmphitheaterForm {
    /**
     * @description Define the country form
     * @param fb
     */
    static AmphitheaterForm(fb: FormBuilder, business: string): FormGroup {
        var AmphitheaterForm: FormGroup;

        AmphitheaterForm = fb.group({
            id: "",
            idAmphitheater: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(10),
                Validators.pattern(Constants.CODE_PATTERN)
            ])],
            nameAmphitheater: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            statuss: [1, Validators.compose([
                Validators.required
            ])],
            // englishName: ["", Validators.compose([
            //     Validators.required,
            //     Validators.maxLength(50),
            //     Validators.pattern(Constants.NAME_PATTERN)
            // ])],
        });
        return AmphitheaterForm;
    }

    /**
     * @description : set the inital value for the form
     * @param countryForm : country form
     * @param country : Data used to set up for form
     */
    static bindingData(amphitheaterForm: FormGroup, Amphitheater: Amphitheater) {
        debugger
        amphitheaterForm.patchValue({
            idAmphitheater: Amphitheater.idAmphitheater,
            nameAmphitheater: Amphitheater.nameAmphitheater,
            statuss: Amphitheater.statuss
        });

    }
}
