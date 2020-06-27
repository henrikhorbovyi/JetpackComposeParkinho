package io.henrikhorbovyi.jetpackcomposeparkinho.ui.animation

import androidx.animation.AnimationBuilder
import androidx.animation.TweenBuilder
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import io.henrikhorbovyi.jetpackcomposeparkinho.ui.Center
import io.henrikhorbovyi.jetpackcomposeparkinho.ui.SampleActivity

class CrossfadeActivity : SampleActivity() {

    @Composable
    override fun Sample() {
        CrossfadeSample()
    }

    @Composable
    @Preview("crossfadePreview", showDecoration = true)
    override fun preview() {
        CrossfadeSample()
    }
}

@Composable
fun CrossfadeSample() {

    Center {
        SimpleTextCrossfade()
        ColorCrossfade()
    }

}

@Composable
fun SimpleTextCrossfade() {
    val greetings = listOf("Olá mundo!", "Olá Androiders \uD83D\uDE0B")
    var currentString by state { greetings[0] }
    /**
     * Nesse exemplo estamos alterando um texto cada vez que o usuário
     * clicar no botão [Animar].
     *
     * A função [Crossfade] vai tomar conta de deixar essa transição
     * entre textos um pouco mais suave :)
     * */
    Center(modifier = Modifier.height(160.dp)) {
        Crossfade(current = currentString) { text ->
            Text(text, fontSize = 28.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            text = { Text("Animar") },
            onClick = {
                currentString = if (currentString == greetings[0]) greetings[1] else greetings[0]
            }
        )
    }
}

@Composable
fun ColorCrossfade() {
    val colors = listOf(Color.Red, Color.Green)
    var currentColor by state { colors[0] }

    /**
     * Podemos passar um [AnimationBuilder] para a função Crossfade,
     * nesse caso estou provendo um [TweenBuilder] e estou alterando a
     * propriedade [duration] para 2000 milissegundos, essa será
     * a duração da nossa transição de cores.
     * */
    val animation = TweenBuilder<Float>().apply {
        duration = 2000
    }

    Crossfade(current = currentColor, animation = animation) { color ->
        Box(
            Modifier.size(120.dp),
            backgroundColor = color,
            shape = RoundedCornerShape(8.dp)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(text = { Text("Mudar a cor") }, onClick = {
        currentColor = if (currentColor == colors[0]) colors[1] else colors[0]
    })
}