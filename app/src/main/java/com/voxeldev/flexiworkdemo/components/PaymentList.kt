package com.voxeldev.flexiworkdemo.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.voxeldev.flexiworkdemo.models.Payment
import com.voxeldev.flexiworkdemo.models.coworkingList
import com.voxeldev.flexiworkdemo.models.paymentListExample

@Composable
fun PaymentList(
    list: List<Payment>,
    onRemoveClicked: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
    ) {
        items(items = list, key = { it.paymentId }) { payment ->
            PaymentListItem(
                payment = payment,
                onRemoveClicked = { onRemoveClicked(payment.paymentId) },
            )
        }
    }
}

@Composable
fun PaymentListItem(
    payment: Payment,
    onRemoveClicked: () -> Unit,
) {
    FlexiElevatedCard(
        modifier = Modifier,
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            IconButton(
                modifier = Modifier
                    .padding(all = 8.dp),
                enabled = !payment.proof,
                onClick = { onRemoveClicked() }
            ) {
                Icon(
                    modifier = Modifier
                        .zIndex(zIndex = 1f)
                        .size(size = 40.dp)
                        .background(
                            color = when (payment.proof) {
                                false -> MaterialTheme.colorScheme.primary
                                true -> MaterialTheme.colorScheme.inversePrimary
                            },
                            shape = CircleShape
                        )
                        .padding(all = 8.dp),
                    imageVector = when (payment.proof) {
                        false -> Icons.Default.Delete
                        true -> Icons.Default.Check
                    },
                    contentDescription = "Удалить",
                    tint = MaterialTheme.colorScheme.surface,
                )
            }

            Image(
                modifier = Modifier
                    .zIndex(zIndex = -1f)
                    .height(height = 180.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    ),
                painter = painterResource(id = coworkingList[payment.coworkingId].image),
                contentDescription = coworkingList[payment.coworkingId].name,
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(height = 16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = coworkingList[payment.coworkingId].name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                overflow = TextOverflow.Ellipsis,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .size(size = 20.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = "Оценка",
                )

                Spacer(modifier = Modifier.width(width = 4.dp))

                Text(
                    text = "${coworkingList[payment.coworkingId].rating}/5",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Spacer(modifier = Modifier.height(height = 4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val dateTime = payment.dateTime
            val sequence = dateTime.splitToSequence(' ').toList()
            val date = sequence[0]
            val time = sequence[1]

            Icon(
                modifier = Modifier
                    .size(size = 16.dp),
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "Рабочие места",
            )

            Spacer(modifier = Modifier.width(width = 4.dp))

            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Icon(
                modifier = Modifier
                    .size(size = 16.dp),
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Компьютеры",
            )

            Spacer(modifier = Modifier.width(width = 4.dp))

            Text(
                text = time,
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Icon(
                modifier = Modifier
                    .size(size = 16.dp),
                imageVector = Icons.Default.Money,
                contentDescription = "Компьютеры",
            )

            Spacer(modifier = Modifier.width(width = 4.dp))

            Text(
                text = payment.paymentType,
                style = MaterialTheme.typography.bodyMedium
            )

        }

        Spacer(modifier = Modifier.width(width = 4.dp))




    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PaymentListPreview() {
    PreviewBase {
        PaymentList(
            list = paymentListExample,
            onRemoveClicked = {},
        )
    }
}
