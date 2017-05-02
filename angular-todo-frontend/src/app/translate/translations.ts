import { InjectionToken } from '@angular/core';

import { LANG_EN } from './lang-en';
import { LANG_NO } from './lang-no';

export const TRANSLATIONS = new InjectionToken('translations');

export const DICTIONARY = {
  'en': LANG_EN,
  'no': LANG_NO
};

export const TRANSLATION_PROVIDERS = [
  { provide: TRANSLATIONS, useValue: DICTIONARY },
];
