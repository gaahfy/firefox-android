/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.settings.quicksettings.protections.cookiebanners.dialog

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.mozilla.fenix.R
import org.mozilla.fenix.compose.button.TextButton
import org.mozilla.fenix.theme.FirefoxTheme
import org.mozilla.fenix.theme.defaultTypography

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
private fun CookieBannerReEngagementDialogComposePreview() {
    FirefoxTheme {
        CookieBannerReEngagementDialogCompose(
            dialogTitle = "Allow Firefox to reject cookie banners?",
            dialogText =
            "Automatically reject cookie requests, when possible. Otherwise, " +
                "accept all cookies to dismiss cookie banners.",
            onAllowButtonClicked = {},
            onNotNowButtonClicked = {},
            onCloseButtonClicked = {},
            allowButtonText = "Dismiss banners",
            declineButtonText = "NOT NOW",
        )
    }
}

/**
 * Displays the cookie banner reducer dialog
 */
@Suppress("LongParameterList", "LongMethod")
@Composable
fun CookieBannerReEngagementDialogCompose(
    dialogTitle: String,
    dialogText: String,
    allowButtonText: String,
    declineButtonText: String,
    onCloseButtonClicked: () -> Unit,
    onAllowButtonClicked: () -> Unit,
    onNotNowButtonClicked: () -> Unit,
) {
    Dialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        onDismissRequest = onNotNowButtonClicked,
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(color = FirefoxTheme.colors.layer1),
        ) {
            Column {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(48.dp),
                    onClick = onCloseButtonClicked,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.mozac_ic_cross_24),
                        contentDescription = stringResource(R.string.content_description_close_button),
                        tint = FirefoxTheme.colors.iconPrimary,
                    )
                }
                Text(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 8.dp,
                    ),
                    color = FirefoxTheme.colors.textPrimary,
                    text = dialogTitle,
                    style = defaultTypography.headline7,
                )
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = FirefoxTheme.colors.textPrimary,
                    fontSize = 16.sp,
                    text = dialogText,
                    style = defaultTypography.body1,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 8.dp,
                        alignment = Alignment.End,
                    ),
                ) {
                    TextButton(
                        text = declineButtonText,
                        onClick = onNotNowButtonClicked,
                    )
                    TextButton(
                        text = allowButtonText,
                        onClick = onAllowButtonClicked,
                    )
                }
            }
        }
    }
}
