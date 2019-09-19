package keijumt.transition

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import keijumt.transition.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        binding.button1.setOnClickListener {
            // レイアウトの変更が起きるまでアニメーションを遅延する
            TransitionManager.beginDelayedTransition(
                binding.rootConstraint,
                AutoTransition().setDuration(1000L)
            )

            val constraintSet = ConstraintSet().also {
                it.clone(binding.rootConstraint)
            }

            // button2のtopの制約を削除
            constraintSet.clear(binding.button2.id, ConstraintSet.TOP)
            // button2の制約の追加(app:layout_constraintBottom_toBottomOf="parent")
            constraintSet.connect(
                binding.button2.id,
                ConstraintSet.BOTTOM,
                binding.rootConstraint.id,
                ConstraintSet.BOTTOM)

            // レイアウトを変更, アニメーションが走る
            constraintSet.applyTo(binding.rootConstraint)
        }

        binding.button2.setOnClickListener {
            TransitionManager.beginDelayedTransition(
                binding.rootConstraint,
                AutoTransition().setDuration(1000L)
            )

            val constraintSet = ConstraintSet().also {
                it.clone(binding.rootConstraint)
            }

            constraintSet.clear(binding.button2.id, ConstraintSet.BOTTOM)
            constraintSet.connect(
                binding.button2.id,
                ConstraintSet.TOP,
                binding.button1.id,
                ConstraintSet.BOTTOM)

            constraintSet.applyTo(binding.rootConstraint)
        }
    }
}
