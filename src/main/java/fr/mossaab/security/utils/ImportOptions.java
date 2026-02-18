package fr.mossaab.security.utils;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ImportOptions {
    @Builder.Default
    boolean skipExisting = false;
    @Builder.Default
    boolean replaceExisting = false;
    @Builder.Default
    boolean strictValidation = false;
    @Builder.Default
    boolean stopOnError = false;
    @Builder.Default
    int sheetIndex = 0;
    @Builder.Default
    int headerRowIndex = 0;
    LocalDate startDate;
    LocalDate endDate;
}
