package pham.honestbee.usertracking.binding

/**
 * Created by Pham on 27/8/2018.
 */
class AppDataBindingComponent : android.databinding.DataBindingComponent {
    override fun getRecyclerViewDataBinding(): RecyclerViewDataBinding {
        return RecyclerViewDataBinding()
    }
}
