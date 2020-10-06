package cursos.alain.eventually_v2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.OnboardingItem;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboradingViewHolder> {

    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }


    @NonNull
    @Override
    public OnboradingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboradingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_ntainer_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboradingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboradingViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageOnboarding;


        OnboradingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOnboarding = itemView.findViewById(R.id.imageOnBoarding);
        }

        void setOnboardingData(OnboardingItem onboardingItem){
            imageOnboarding.setImageResource(onboardingItem.getImage());
        }
    }
}
