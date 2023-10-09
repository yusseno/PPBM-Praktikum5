package utdi.yusseno.praktikum5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utdi.yusseno.praktikum5.ui.theme.Praktikum5Theme

// Data class untuk merepresentasikan pemain
data class Player(
    val name: String,
    val dateOfBirth: String,
    val nationality: String,
    val jerseyNumber: String,
    val cleanSheet: String,
    val timePlayed: String,
    val appearances: String,
    val imageResId: Int // ID sumber daya gambar pemain
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktikum5Theme {
                // Container utama aplikasi menggunakan Surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Data pemain diinisialisasi di sini
                    val players = listOf(
                        Player("Álisson Ramsés Becker", "2 October 1992 (age 31)", "Brazil", "1", "2", "810", "9", R.drawable.alison),
                        Player("Adrián San Miguel", "3 Januari 1987 (age 36)", "Spain", "13", "0", "0", "0", R.drawable.adrian),
                        Player("Caoimhín Kelleher", "23 November 1998 (age 25)", "Irlandia", "62", "0", "180", "2", R.drawable.kelleher)
                    )
                    // Menampilkan halaman dengan pemain-pemain yang telah diinisialisasi
                    Page(players = players)
                }
            }
        }
    }
}

@Composable
fun Header(name: String, modifier: Modifier = Modifier) {
    // Komponen Header menampilkan judul halaman
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Liverpool players'",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 26.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Goalkeeper in 2023-2024",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 26.sp)
        )
    }
}

@Composable
fun ImageFuture(imageResId: Int, modifier: Modifier = Modifier) {
    // Komponen untuk menampilkan gambar pemain
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp, top = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(240.dp)
        )
    }
}

@Composable
fun Deskripsi(player: Player, modifier: Modifier = Modifier) {
    // Komponen untuk menampilkan deskripsi pemain
    Column(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp)
    ) {
        val labels = listOf("Full Name", "Date of Birth", "National", "Jersey Number", "Clean Sheet", "Time Played", "Appearances")
        val values = listOf(player.name, player.dateOfBirth, player.nationality, player.jerseyNumber, player.cleanSheet, player.timePlayed, player.appearances)

        for (i in labels.indices) {
            // Setiap label dan nilainya ditampilkan dalam satu baris
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = labels[i],
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(2f)
                        .padding(30.dp, 0.dp)
                )
                Text(
                    text = values[i],
                    modifier = Modifier.weight(2f)
                )
            }
        }
    }
}

@Composable
fun NavigationButtons(
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit
) {
    // Komponen untuk tombol navigasi maju dan mundur
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.padding(end = 155.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Button(onClick = onNextClick) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun Page(players: List<Player>) {
    // Komponen untuk menampilkan halaman dengan pemain dan tombol navigasi
    var selectedPlayerIndex by remember { mutableStateOf(0) }
    val selectedPlayer = players[selectedPlayerIndex]

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Header("")
        ImageFuture(selectedPlayer.imageResId)
        Deskripsi(selectedPlayer)
        Spacer(modifier = Modifier.height(20.dp))
        // Menambahkan tombol navigasi untuk pemain berikutnya dan sebelumnya
        NavigationButtons(
            onNextClick = {
                selectedPlayerIndex = (selectedPlayerIndex + 1) % players.size
            }
        ) {
            selectedPlayerIndex = (selectedPlayerIndex - 1 + players.size) % players.size
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PagePreview() {
    // Fungsi ini digunakan untuk menampilkan tampilan pratinjau dalam Android Studio
    Praktikum5Theme {
        val players = listOf(
            Player("Álisson Ramsés Becker", "2 October 1992 (age 31)", "Brazil", "1", "2", "810", "8", R.drawable.alison)
        )
    }
}
