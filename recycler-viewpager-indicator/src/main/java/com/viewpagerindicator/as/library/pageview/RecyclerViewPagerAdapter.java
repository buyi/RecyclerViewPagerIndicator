package com.viewpagerindicator.as.library.pageview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerViewPagerAdapter
 * Adapter wrapper.
 *
 * @author Green
 */
// 主要实现三个方法
//1.onCreateViewHolder
//2.onBindViewHolder
//3.getItemCount

///**
// * Base class for an Adapter
// *
// * <p>Adaptersde a binding from an app-specific data set to views that are displayed
// * within a {@link RecyclerView}.</p>
// */
//public static abstract class Adapter<VH extends ViewHolder> {
//    private final AdapterDataObservable mObservable = new AdapterDataObservable();
//    private boolean mHasStableIds = false;
//
//    /**
//     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
//     * an item.
//     * <p>
//     * This new ViewHolder should be constructed with a new View that can represent the items
//     * of the given type. You can either create a new View manually or inflate it from an XML
//     * layout file.
//     * <p>
//     * The new ViewHolder will be used to display items of the adapter using
//     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
//     * different items in the data set, it is a good idea to cache references to sub views of
//     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
//     *
//     * @param parent The ViewGroup into which the new View will be added after it is bound to
//     *               an adapter position.
//     * @param viewType The view type of the new View.
//     *
//     * @return A new ViewHolder that holds a View of the given view type.
//     * @see #getItemViewType(int)
//     * @see #onBindViewHolder(ViewHolder, int)
//     */
//    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);
//
//    /**
//     * Called by RecyclerView to display the data at the specified position. This method should
//     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
//     * position.
//     * <p>
//     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
//     * again if the position of the item changes in the data set unless the item itself is
//     * invalidated or the new position cannot be determined. For this reason, you should only
//     * use the <code>position</code> parameter while acquiring the related data item inside
//     * this method and should not keep a copy of it. If you need the position of an item later
//     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
//     * have the updated adapter position.
//     *
//     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
//     * handle effcient partial bind.
//     *
//     * @param holder The ViewHolder which should be updated to represent the contents of the
//     *        item at the given position in the data set.
//     * @param position The position of the item within the adapter's data set.
//     */
//    public abstract void onBindViewHolder(VH holder, int position);
//
//    /**
//     * Called by RecyclerView to display the data at the specified position. This method
//     * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
//     * the given position.
//     * <p>
//     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
//     * again if the position of the item changes in the data set unless the item itself is
//     * invalidated or the new position cannot be determined. For this reason, you should only
//     * use the <code>position</code> parameter while acquiring the related data item inside
//     * this method and should not keep a copy of it. If you need the position of an item later
//     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
//     * have the updated adapter position.
//     * <p>
//     * Partial bind vs full bind:
//     * <p>
//     * The payloads parameter is a merge list from {@link #notifyItemChanged(int, Object)} or
//     * {@link #notifyItemRangeChanged(int, int, Object)}.  If the payloads list is not empty,
//     * the ViewHolder is currently bound to old data and Adapter may run an efficient partial
//     * update using the payload info.  If the payload is empty,  Adapter must run a full bind.
//     * Adapter should not assume that the payload passed in notify methods will be received by
//     * onBindViewHolder().  For example when the view is not attached to the screen, the
//     * payload in notifyItemChange() will be simply dropped.
//     *
//     * @param holder The ViewHolder which should be updated to represent the contents of the
//     *               item at the given position in the data set.
//     * @param position The position of the item within the adapter's data set.
//     * @param payloads A non-null list of merged payloads. Can be empty list if requires full
//     *                 update.
//     */
//    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
//        onBindViewHolder(holder, position);
//    }
//
//    /**
//     * This method calls {@link #onCreateViewHolder(ViewGroup, int)} to create a new
//     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
//     *
//     * @see #onCreateViewHolder(ViewGroup, int)
//     */
//    public final VH createViewHolder(ViewGroup parent, int viewType) {
//        TraceCompat.beginSection(TRACE_CREATE_VIEW_TAG);
//        final VH holder = onCreateViewHolder(parent, viewType);
//        holder.mItemViewType = viewType;
//        TraceCompat.endSection();
//        return holder;
//    }
//
//    /**
//     * This method internally calls {@link #onBindViewHolder(ViewHolder, int)} to update the
//     * {@link ViewHolder} contents with the item at the given position and also sets up some
//     * private fields to be used by RecyclerView.
//     *
//     * @see #onBindViewHolder(ViewHolder, int)
//     */
//    public final void bindViewHolder(VH holder, int position) {
//        holder.mPosition = position;
//        if (hasStableIds()) {
//            holder.mItemId = getItemId(position);
//        }
//        holder.setFlags(ViewHolder.FLAG_BOUND,
//                ViewHolder.FLAG_BOUND | ViewHolder.FLAG_UPDATE | ViewHolder.FLAG_INVALID
//                        | ViewHolder.FLAG_ADAPTER_POSITION_UNKNOWN);
//        TraceCompat.beginSection(TRACE_BIND_VIEW_TAG);
//        onBindViewHolder(holder, position, holder.getUnmodifiedPayloads());
//        holder.clearPayload();
//        TraceCompat.endSection();
//    }
//
//    /**
//     * Return the view type of the item at <code>position</code> for the purposes
//     * of view recycling.
//     *
//     * <p>The default implementation of this method returns 0, making the assumption of
//     * a single view type for the adapter. Unlike ListView adapters, types need not
//     * be contiguous. Consider using id resources to uniquely identify item view types.
//     *
//     * @param position position to query
//     * @return integer value identifying the type of the view needed to represent the item at
//     *                 <code>position</code>. Type codes need not be contiguous.
//     */
//    public int getItemViewType(int position) {
//        return 0;
//    }
//
//    /**
//     * Indicates whether each item in the data set can be represented with a unique identifier
//     * of type {@link java.lang.Long}.
//     *
//     * @param hasStableIds Whether items in data set have unique identifiers or not.
//     * @see #hasStableIds()
//     * @see #getItemId(int)
//     */
//    public void setHasStableIds(boolean hasStableIds) {
//        if (hasObservers()) {
//            throw new IllegalStateException("Cannot change whether this adapter has " +
//                    "stable IDs while the adapter has registered observers.");
//        }
//        mHasStableIds = hasStableIds;
//    }
//
//    /**
//     * Return the stable ID for the item at <code>position</code>. If {@link #hasStableIds()}
//     * would return false this method should return {@link #NO_ID}. The default implementation
//     * of this method returns {@link #NO_ID}.
//     *
//     * @param position Adapter position to query
//     * @return the stable ID of the item at position
//     */
//    public long getItemId(int position) {
//        return NO_ID;
//    }
//
//    /**
//     * Returns the total number of items in the data set hold by the adapter.
//     *
//     * @return The total number of items in this adapter.
//     */
//    public abstract int getItemCount();
//
//    /**
//     * Returns true if this adapter publishes a unique <code>long</code> value that can
//     * act as a key for the item at a given position in the data set. If that item is relocated
//     * in the data set, the ID returned for that item should be the same.
//     *
//     * @return true if this adapter's items have stable IDs
//     */
//    public final boolean hasStableIds() {
//        return mHasStableIds;
//    }
//
//    /**
//     * Called when a view created by this adapter has been recycled.
//     *
//     * <p>A view is recycled when a {@link LayoutManager} decides that it no longer
//     * needs to be attached to its parent {@link RecyclerView}. This can be because it has
//     * fallen out of visibility or a set of cached views represented by views still
//     * attached to the parent RecyclerView. If an item view has large or expensive data
//     * bound to it such as large bitmaps, this may be a good place to release those
//     * resources.</p>
//     * <p>
//     * RecyclerView calls this method right before clearing ViewHolder's internal data and
//     * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
//     * before being recycled, you can call {@link ViewHolder#getAdapterPosition()} to get
//     * its adapter position.
//     *
//     * @param holder The ViewHolder for the view being recycled
//     */
//    public void onViewRecycled(VH holder) {
//    }
//
//    /**
//     * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
//     * due to its transient state. Upon receiving this callback, Adapter can clear the
//     * animation(s) that effect the View's transient state and return <code>true</code> so that
//     * the View can be recycled. Keep in mind that the View in question is already removed from
//     * the RecyclerView.
//     * <p>
//     * In some cases, it is acceptable to recycle a View although it has transient state. Most
//     * of the time, this is a case where the transient state will be cleared in
//     * {@link #onBindViewHolder(ViewHolder, int)} call when View is rebound to a new position.
//     * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
//     * value of this method to decide whether the View should be recycled or not.
//     * <p>
//     * Note that when all animations are created by {@link RecyclerView.ItemAnimator}, you
//     * should never receive this callback because RecyclerView keeps those Views as children
//     * until their animations are complete. This callback is useful when children of the item
//     * views create animations which may not be easy to implement using an {@link ItemAnimator}.
//     * <p>
//     * You should <em>never</em> fix this issue by calling
//     * <code>holder.itemView.setHasTransientState(false);</code> unless you've previously called
//     * <code>holder.itemView.setHasTransientState(true);</code>. Each
//     * <code>View.setHasTransientState(true)</code> call must be matched by a
//     * <code>View.setHasTransientState(false)</code> call, otherwise, the state of the View
//     * may become inconsistent. You should always prefer to end or cancel animations that are
//     * triggering the transient state instead of handling it manually.
//     *
//     * @param holder The ViewHolder containing the View that could not be recycled due to its
//     *               transient state.
//     * @return True if the View should be recycled, false otherwise. Note that if this method
//     * returns <code>true</code>, RecyclerView <em>will ignore</em> the transient state of
//     * the View and recycle it regardless. If this method returns <code>false</code>,
//     * RecyclerView will check the View's transient state again before giving a final decision.
//     * Default implementation returns false.
//     */
//    public boolean onFailedToRecycleView(VH holder) {
//        return false;
//    }
//
//    /**
//     * Called when a view created by this adapter has been attached to a window.
//     *
//     * <p>This can be used as a reasonable signal that the view is about to be seen
//     * by the user. If the adapter previously freed any resources in
//     * {@link #onViewDetachedFromWindow(RecyclerView.ViewHolder) onViewDetachedFromWindow}
//     * those resources should be restored here.</p>
//     *
//     * @param holder Holder of the view being attached
//     */
//    public void onViewAttachedToWindow(VH holder) {
//    }
//
//    /**
//     * Called when a view created by this adapter has been detached from its window.
//     *
//     * <p>Becoming detached from the window is not necessarily a permanent condition;
//     * the consumer of an Adapter's views may choose to cache views offscreen while they
//     * are not visible, attaching an detaching them as appropriate.</p>
//     *
//     * @param holder Holder of the view being detached
//     */
//    public void onViewDetachedFromWindow(VH holder) {
//    }
//
//    /**
//     * Returns true if one or more observers are attached to this adapter.
//     *
//     * @return true if this adapter has observers
//     */
//    public final boolean hasObservers() {
//        return mObservable.hasObservers();
//    }
//
//    /**
//     * Register a new observer to listen for data changes.
//     *
//     * <p>The adapter may publish a variety of events describing specific changes.
//     * Not all adapters may support all change types and some may fall back to a generic
//     * {@link android.support.v7.widget.RecyclerView.AdapterDataObserver#onChanged()
//     * "something changed"} event if more specific data is not available.</p>
//     *
//     * <p>Components registering observers with an adapter are responsible for
//     * {@link #unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver)
//     * unregistering} those observers when finished.</p>
//     *
//     * @param observer Observer to register
//     *
//     * @see #unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver)
//     */
//    public void registerAdapterDataObserver(AdapterDataObserver observer) {
//        mObservable.registerObserver(observer);
//    }
//
//    /**
//     * Unregister an observer currently listening for data changes.
//     *
//     * <p>The unregistered observer will no longer receive events about changes
//     * to the adapter.</p>
//     *
//     * @param observer Observer to unregister
//     *
//     * @see #registerAdapterDataObserver(RecyclerView.AdapterDataObserver)
//     */
//    public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
//        mObservable.unregisterObserver(observer);
//    }
//
//    /**
//     * Called by RecyclerView when it starts observing this Adapter.
//     * <p>
//     * Keep in mind that same adapter may be observed by multiple RecyclerViews.
//     *
//     * @param recyclerView The RecyclerView instance which started observing this adapter.
//     * @see #onDetachedFromRecyclerView(RecyclerView)
//     */
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//    }
//
//    /**
//     * Called by RecyclerView when it stops observing this Adapter.
//     *
//     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
//     * @see #onAttachedToRecyclerView(RecyclerView)
//     */
//    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
//    }
//
//    /**
//     * Notify any registered observers that the data set has changed.
//     *
//     * <p>There are two different classes of data change events, item changes and structural
//     * changes. Item changes are when a single item has its data updated but no positional
//     * changes have occurred. Structural changes are when items are inserted, removed or moved
//     * within the data set.</p>
//     *
//     * <p>This event does not specify what about the data set has changed, forcing
//     * any observers to assume that all existing items and structure may no longer be valid.
//     * LayoutManagers will be forced to fully rebind and relayout all visible views.</p>
//     *
//     * <p><code>RecyclerView</code> will attempt to synthesize visible structural change events
//     * for adapters that report that they have {@link #hasStableIds() stable IDs} when
//     * this method is used. This can help for the purposes of animation and visual
//     * object persistence but individual item views will still need to be rebound
//     * and relaid out.</p>
//     *
//     * <p>If you are writing an adapter it will always be more efficient to use the more
//     * specific change events if you can. Rely on <code>notifyDataSetChanged()</code>
//     * as a last resort.</p>
//     *
//     * @see #notifyItemChanged(int)
//     * @see #notifyItemInserted(int)
//     * @see #notifyItemRemoved(int)
//     * @see #notifyItemRangeChanged(int, int)
//     * @see #notifyItemRangeInserted(int, int)
//     * @see #notifyItemRangeRemoved(int, int)
//     */
//    public final void notifyDataSetChanged() {
//        mObservable.notifyChanged();
//    }
//
//    /**
//     * Notify any registered observers that the item at <code>position</code> has changed.
//     * Equivalent to calling <code>notifyItemChanged(position, null);</code>.
//     *
//     * <p>This is an item change event, not a structural change event. It indicates that any
//     * reflection of the data at <code>position</code> is out of date and should be updated.
//     * The item at <code>position</code> retains the same identity.</p>
//     *
//     * @param position Position of the item that has changed
//     *
//     * @see #notifyItemRangeChanged(int, int)
//     */
//    public final void notifyItemChanged(int position) {
//        mObservable.notifyItemRangeChanged(position, 1);
//    }
//
//    /**
//     * Notify any registered observers that the item at <code>position</code> has changed with an
//     * optional payload object.
//     *
//     * <p>This is an item change event, not a structural change event. It indicates that any
//     * reflection of the data at <code>position</code> is out of date and should be updated.
//     * The item at <code>position</code> retains the same identity.
//     * </p>
//     *
//     * <p>
//     * Client can optionally pass a payload for partial change. These payloads will be merged
//     * and may be passed to adapter's {@link #onBindViewHolder(ViewHolder, int, List)} if the
//     * item is already represented by a ViewHolder and it will be rebound to the same
//     * ViewHolder. A notifyItemRangeChanged() with null payload will clear all existing
//     * payloads on that item and prevent future payload until
//     * {@link #onBindViewHolder(ViewHolder, int, List)} is called. Adapter should not assume
//     * that the payload will always be passed to onBindViewHolder(), e.g. when the view is not
//     * attached, the payload will be simply dropped.
//     *
//     * @param position Position of the item that has changed
//     * @param payload Optional parameter, use null to identify a "full" update
//     *
//     * @see #notifyItemRangeChanged(int, int)
//     */
//    public final void notifyItemChanged(int position, Object payload) {
//        mObservable.notifyItemRangeChanged(position, 1, payload);
//    }
//
//    /**
//     * Notify any registered observers that the <code>itemCount</code> items starting at
//     * position <code>positionStart</code> have changed.
//     * Equivalent to calling <code>notifyItemRangeChanged(position, itemCount, null);</code>.
//     *
//     * <p>This is an item change event, not a structural change event. It indicates that
//     * any reflection of the data in the given position range is out of date and should
//     * be updated. The items in the given range retain the same identity.</p>
//     *
//     * @param positionStart Position of the first item that has changed
//     * @param itemCount Number of items that have changed
//     *
//     * @see #notifyItemChanged(int)
//     */
//    public final void notifyItemRangeChanged(int positionStart, int itemCount) {
//        mObservable.notifyItemRangeChanged(positionStart, itemCount);
//    }
//
//    /**
//     * Notify any registered observers that the <code>itemCount</code> items starting at
//     * position<code>positionStart</code> have changed. An optional payload can be
//     * passed to each changed item.
//     *
//     * <p>This is an item change event, not a structural change event. It indicates that any
//     * reflection of the data in the given position range is out of date and should be updated.
//     * The items in the given range retain the same identity.
//     * </p>
//     *
//     * <p>
//     * Client can optionally pass a payload for partial change. These payloads will be merged
//     * and may be passed to adapter's {@link #onBindViewHolder(ViewHolder, int, List)} if the
//     * item is already represented by a ViewHolder and it will be rebound to the same
//     * ViewHolder. A notifyItemRangeChanged() with null payload will clear all existing
//     * payloads on that item and prevent future payload until
//     * {@link #onBindViewHolder(ViewHolder, int, List)} is called. Adapter should not assume
//     * that the payload will always be passed to onBindViewHolder(), e.g. when the view is not
//     * attached, the payload will be simply dropped.
//     *
//     * @param positionStart Position of the first item that has changed
//     * @param itemCount Number of items that have changed
//     * @param payload  Optional parameter, use null to identify a "full" update
//     *
//     * @see #notifyItemChanged(int)
//     */
//    public final void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
//        mObservable.notifyItemRangeChanged(positionStart, itemCount, payload);
//    }
//
//    /**
//     * Notify any registered observers that the item reflected at <code>position</code>
//     * has been newly inserted. The item previously at <code>position</code> is now at
//     * position <code>position + 1</code>.
//     *
//     * <p>This is a structural change event. Representations of other existing items in the
//     * data set are still considered up to date and will not be rebound, though their
//     * positions may be altered.</p>
//     *
//     * @param position Position of the newly inserted item in the data set
//     *
//     * @see #notifyItemRangeInserted(int, int)
//     */
//    public final void notifyItemInserted(int position) {
//        mObservable.notifyItemRangeInserted(position, 1);
//    }
//
//    /**
//     * Notify any registered observers that the item reflected at <code>fromPosition</code>
//     * has been moved to <code>toPosition</code>.
//     *
//     * <p>This is a structural change event. Representations of other existing items in the
//     * data set are still considered up to date and will not be rebound, though their
//     * positions may be altered.</p>
//     *
//     * @param fromPosition Previous position of the item.
//     * @param toPosition New position of the item.
//     */
//    public final void notifyItemMoved(int fromPosition, int toPosition) {
//        mObservable.notifyItemMoved(fromPosition, toPosition);
//    }
//
//    /**
//     * Notify any registered observers that the currently reflected <code>itemCount</code>
//     * items starting at <code>positionStart</code> have been newly inserted. The items
//     * previously located at <code>positionStart</code> and beyond can now be found starting
//     * at position <code>positionStart + itemCount</code>.
//     *
//     * <p>This is a structural change event. Representations of other existing items in the
//     * data set are still considered up to date and will not be rebound, though their positions
//     * may be altered.</p>
//     *
//     * @param positionStart Position of the first item that was inserted
//     * @param itemCount Number of items inserted
//     *
//     * @see #notifyItemInserted(int)
//     */
//    public final void notifyItemRangeInserted(int positionStart, int itemCount) {
//        mObservable.notifyItemRangeInserted(positionStart, itemCount);
//    }
//
//    /**
//     * Notify any registered observers that the item previously located at <code>position</code>
//     * has been removed from the data set. The items previously located at and after
//     * <code>position</code> may now be found at <code>oldPosition - 1</code>.
//     *
//     * <p>This is a structural change event. Representations of other existing items in the
//     * data set are still considered up to date and will not be rebound, though their positions
//     * may be altered.</p>
//     *
//     * @param position Position of the item that has now been removed
//     *
//     * @see #notifyItemRangeRemoved(int, int)
//     */
//    public final void notifyItemRemoved(int position) {
//        mObservable.notifyItemRangeRemoved(position, 1);
//    }
//
//    /**
//     * Notify any registered observers that the <code>itemCount</code> items previously
//     * located at <code>positionStart</code> have been removed from the data set. The items
//     * previously located at and after <code>positionStart + itemCount</code> may now be found
//     * at <code>oldPosition - itemCount</code>.
//     *
//     * <p>This is a structural change event. Representations of other existing items in the data
//     * set are still considered up to date and will not be rebound, though their positions
//     * may be altered.</p>
//     *
//     * @param positionStart Previous position of the first item that was removed
//     * @param itemCount Number of items removed from the data set
//     */
//    public final void notifyItemRangeRemoved(int positionStart, int itemCount) {
//        mObservable.notifyItemRangeRemoved(positionStart, itemCount);
//    }
//}

// ViewHolder 有个 itemView
///**
// * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
// *
// * <p>{@link Adapter} implementations should subclass ViewHolder and add fields for caching
// * potentially expensive {@link View#findViewById(int)} results.</p>
// *
// * <p>While {@link LayoutParams} belong to the {@link LayoutManager},
// * {@link ViewHolder ViewHolders} belong to the adapter. Adapters should feel free to use
// * their own custom ViewHolder implementations to store data that makes binding view contents
// * easier. Implementations should assume that individual item views will hold strong references
// * to <code>ViewHolder</code> objects and that <code>RecyclerView</code> instances may hold
// * strong references to extra off-screen item views for caching purposes</p>
// */
//public static abstract class ViewHolder {
//    public final View itemView;
//    int mPosition = NO_POSITION;
//    int mOldPosition = NO_POSITION;
//    long mItemId = NO_ID;
//    int mItemViewType = INVALID_TYPE;
//    int mPreLayoutPosition = NO_POSITION;
//
//    // The item that this holder is shadowing during an item change event/animation
//    ViewHolder mShadowedHolder = null;
//    // The item that is shadowing this holder during an item change event/animation
//    ViewHolder mShadowingHolder = null;
//
//    /**
//     * This ViewHolder has been bound to a position; mPosition, mItemId and mItemViewType
//     * are all valid.
//     */
//    static final int FLAG_BOUND = 1 << 0;
//
//    /**
//     * The data this ViewHolder's view reflects is stale and needs to be rebound
//     * by the adapter. mPosition and mItemId are consistent.
//     */
//    static final int FLAG_UPDATE = 1 << 1;
//
//    /**
//     * This ViewHolder's data is invalid. The identity implied by mPosition and mItemId
//     * are not to be trusted and may no longer match the item view type.
//     * This ViewHolder must be fully rebound to different data.
//     */
//    static final int FLAG_INVALID = 1 << 2;
//
//    /**
//     * This ViewHolder points at data that represents an item previously removed from the
//     * data set. Its view may still be used for things like outgoing animations.
//     */
//    static final int FLAG_REMOVED = 1 << 3;
//
//    /**
//     * This ViewHolder should not be recycled. This flag is set via setIsRecyclable()
//     * and is intended to keep views around during animations.
//     */
//    static final int FLAG_NOT_RECYCLABLE = 1 << 4;
//
//    /**
//     * This ViewHolder is returned from scrap which means we are expecting an addView call
//     * for this itemView. When returned from scrap, ViewHolder stays in the scrap list until
//     * the end of the layout pass and then recycled by RecyclerView if it is not added back to
//     * the RecyclerView.
//     */
//    static final int FLAG_RETURNED_FROM_SCRAP = 1 << 5;
//
//    /**
//     * This ViewHolder is fully managed by the LayoutManager. We do not scrap, recycle or remove
//     * it unless LayoutManager is replaced.
//     * It is still fully visible to the LayoutManager.
//     */
//    static final int FLAG_IGNORE = 1 << 7;
//
//    /**
//     * When the View is detached form the parent, we set this flag so that we can take correct
//     * action when we need to remove it or add it back.
//     */
//    static final int FLAG_TMP_DETACHED = 1 << 8;
//
//    /**
//     * Set when we can no longer determine the adapter position of this ViewHolder until it is
//     * rebound to a new position. It is different than FLAG_INVALID because FLAG_INVALID is
//     * set even when the type does not match. Also, FLAG_ADAPTER_POSITION_UNKNOWN is set as soon
//     * as adapter notification arrives vs FLAG_INVALID is set lazily before layout is
//     * re-calculated.
//     */
//    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 1 << 9;
//
//    /**
//     * Set when a addChangePayload(null) is called
//     */
//    static final int FLAG_ADAPTER_FULLUPDATE = 1 << 10;
//
//    /**
//     * Used by ItemAnimator when a ViewHolder's position changes
//     */
//    static final int FLAG_MOVED = 1 << 11;
//
//    /**
//     * Used by ItemAnimator when a ViewHolder appears in pre-layout
//     */
//    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 1 << 12;
//
//    /**
//     * Used when a ViewHolder starts the layout pass as a hidden ViewHolder but is re-used from
//     * hidden list (as if it was scrap) without being recycled in between.
//     *
//     * When a ViewHolder is hidden, there are 2 paths it can be re-used:
//     *   a) Animation ends, view is recycled and used from the recycle pool.
//     *   b) LayoutManager asks for the View for that position while the ViewHolder is hidden.
//     *
//     * This flag is used to represent "case b" where the ViewHolder is reused without being
//     * recycled (thus "bounced" from the hidden list). This state requires special handling
//     * because the ViewHolder must be added to pre layout maps for animations as if it was
//     * already there.
//     */
//    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 1 << 13;
//
//    private int mFlags;
//
//    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
//
//    List<Object> mPayloads = null;
//    List<Object> mUnmodifiedPayloads = null;
//
//    private int mIsRecyclableCount = 0;
//
//    // If non-null, view is currently considered scrap and may be reused for other data by the
//    // scrap container.
//    private Recycler mScrapContainer = null;
//    // Keeps whether this ViewHolder lives in Change scrap or Attached scrap
//    private boolean mInChangeScrap = false;
//
//    // Saves isImportantForAccessibility value for the view item while it's in hidden state and
//    // marked as unimportant for accessibility.
//    private int mWasImportantForAccessibilityBeforeHidden =
//            ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO;
//
//    /**
//     * Is set when VH is bound from the adapter and cleaned right before it is sent to
//     * {@link RecycledViewPool}.
//     */
//    RecyclerView mOwnerRecyclerView;
//
//    public ViewHolder(View itemView) {
//        if (itemView == null) {
//            throw new IllegalArgumentException("itemView may not be null");
//        }
//        this.itemView = itemView;
//    }
//
//    void flagRemovedAndOffsetPosition(int mNewPosition, int offset, boolean applyToPreLayout) {
//        addFlags(ViewHolder.FLAG_REMOVED);
//        offsetPosition(offset, applyToPreLayout);
//        mPosition = mNewPosition;
//    }
//
//    void offsetPosition(int offset, boolean applyToPreLayout) {
//        if (mOldPosition == NO_POSITION) {
//            mOldPosition = mPosition;
//        }
//        if (mPreLayoutPosition == NO_POSITION) {
//            mPreLayoutPosition = mPosition;
//        }
//        if (applyToPreLayout) {
//            mPreLayoutPosition += offset;
//        }
//        mPosition += offset;
//        if (itemView.getLayoutParams() != null) {
//            ((LayoutParams) itemView.getLayoutParams()).mInsetsDirty = true;
//        }
//    }
//
//    void clearOldPosition() {
//        mOldPosition = NO_POSITION;
//        mPreLayoutPosition = NO_POSITION;
//    }
//
//    void saveOldPosition() {
//        if (mOldPosition == NO_POSITION) {
//            mOldPosition = mPosition;
//        }
//    }
//
//    boolean shouldIgnore() {
//        return (mFlags & FLAG_IGNORE) != 0;
//    }
//
//    /**
//     * @deprecated This method is deprecated because its meaning is ambiguous due to the async
//     * handling of adapter updates. Please use {@link #getLayoutPosition()} or
//     * {@link #getAdapterPosition()} depending on your use case.
//     *
//     * @see #getLayoutPosition()
//     * @see #getAdapterPosition()
//     */
//    @Deprecated
//    public final int getPosition() {
//        return mPreLayoutPosition == NO_POSITION ? mPosition : mPreLayoutPosition;
//    }
//
//    /**
//     * Returns the position of the ViewHolder in terms of the latest layout pass.
//     * <p>
//     * This position is mostly used by RecyclerView components to be consistent while
//     * RecyclerView lazily processes adapter updates.
//     * <p>
//     * For performance and animation reasons, RecyclerView batches all adapter updates until the
//     * next layout pass. This may cause mismatches between the Adapter position of the item and
//     * the position it had in the latest layout calculations.
//     * <p>
//     * LayoutManagers should always call this method while doing calculations based on item
//     * positions. All methods in {@link RecyclerView.LayoutManager}, {@link RecyclerView.State},
//     * {@link RecyclerView.Recycler} that receive a position expect it to be the layout position
//     * of the item.
//     * <p>
//     * If LayoutManager needs to call an external method that requires the adapter position of
//     * the item, it can use {@link #getAdapterPosition()} or
//     * {@link RecyclerView.Recycler#convertPreLayoutPositionToPostLayout(int)}.
//     *
//     * @return Returns the adapter position of the ViewHolder in the latest layout pass.
//     * @see #getAdapterPosition()
//     */
//    public final int getLayoutPosition() {
//        return mPreLayoutPosition == NO_POSITION ? mPosition : mPreLayoutPosition;
//    }
//
//    /**
//     * Returns the Adapter position of the item represented by this ViewHolder.
//     * <p>
//     * Note that this might be different than the {@link #getLayoutPosition()} if there are
//     * pending adapter updates but a new layout pass has not happened yet.
//     * <p>
//     * RecyclerView does not handle any adapter updates until the next layout traversal. This
//     * may create temporary inconsistencies between what user sees on the screen and what
//     * adapter contents have. This inconsistency is not important since it will be less than
//     * 16ms but it might be a problem if you want to use ViewHolder position to access the
//     * adapter. Sometimes, you may need to get the exact adapter position to do
//     * some actions in response to user events. In that case, you should use this method which
//     * will calculate the Adapter position of the ViewHolder.
//     * <p>
//     * Note that if you've called {@link RecyclerView.Adapter#notifyDataSetChanged()}, until the
//     * next layout pass, the return value of this method will be {@link #NO_POSITION}.
//     *
//     * @return The adapter position of the item if it still exists in the adapter.
//     * {@link RecyclerView#NO_POSITION} if item has been removed from the adapter,
//     * {@link RecyclerView.Adapter#notifyDataSetChanged()} has been called after the last
//     * layout pass or the ViewHolder has already been recycled.
//     */
//    public final int getAdapterPosition() {
//        if (mOwnerRecyclerView == null) {
//            return NO_POSITION;
//        }
//        return mOwnerRecyclerView.getAdapterPositionFor(this);
//    }
//
//    /**
//     * When LayoutManager supports animations, RecyclerView tracks 3 positions for ViewHolders
//     * to perform animations.
//     * <p>
//     * If a ViewHolder was laid out in the previous onLayout call, old position will keep its
//     * adapter index in the previous layout.
//     *
//     * @return The previous adapter index of the Item represented by this ViewHolder or
//     * {@link #NO_POSITION} if old position does not exists or cleared (pre-layout is
//     * complete).
//     */
//    public final int getOldPosition() {
//        return mOldPosition;
//    }
//
//    /**
//     * Returns The itemId represented by this ViewHolder.
//     *
//     * @return The the item's id if adapter has stable ids, {@link RecyclerView#NO_ID}
//     * otherwise
//     */
//    public final long getItemId() {
//        return mItemId;
//    }
//
//    /**
//     * @return The view type of this ViewHolder.
//     */
//    public final int getItemViewType() {
//        return mItemViewType;
//    }
//
//    boolean isScrap() {
//        return mScrapContainer != null;
//    }
//
//    void unScrap() {
//        mScrapContainer.unscrapView(this);
//    }
//
//    boolean wasReturnedFromScrap() {
//        return (mFlags & FLAG_RETURNED_FROM_SCRAP) != 0;
//    }
//
//    void clearReturnedFromScrapFlag() {
//        mFlags = mFlags & ~FLAG_RETURNED_FROM_SCRAP;
//    }
//
//    void clearTmpDetachFlag() {
//        mFlags = mFlags & ~FLAG_TMP_DETACHED;
//    }
//
//    void stopIgnoring() {
//        mFlags = mFlags & ~FLAG_IGNORE;
//    }
//
//    void setScrapContainer(Recycler recycler, boolean isChangeScrap) {
//        mScrapContainer = recycler;
//        mInChangeScrap = isChangeScrap;
//    }
//
//    boolean isInvalid() {
//        return (mFlags & FLAG_INVALID) != 0;
//    }
//
//    boolean needsUpdate() {
//        return (mFlags & FLAG_UPDATE) != 0;
//    }
//
//    boolean isBound() {
//        return (mFlags & FLAG_BOUND) != 0;
//    }
//
//    boolean isRemoved() {
//        return (mFlags & FLAG_REMOVED) != 0;
//    }
//
//    boolean hasAnyOfTheFlags(int flags) {
//        return (mFlags & flags) != 0;
//    }
//
//    boolean isTmpDetached() {
//        return (mFlags & FLAG_TMP_DETACHED) != 0;
//    }
//
//    boolean isAdapterPositionUnknown() {
//        return (mFlags & FLAG_ADAPTER_POSITION_UNKNOWN) != 0 || isInvalid();
//    }
//
//    void setFlags(int flags, int mask) {
//        mFlags = (mFlags & ~mask) | (flags & mask);
//    }
//
//    void addFlags(int flags) {
//        mFlags |= flags;
//    }
//
//    void addChangePayload(Object payload) {
//        if (payload == null) {
//            addFlags(FLAG_ADAPTER_FULLUPDATE);
//        } else if ((mFlags & FLAG_ADAPTER_FULLUPDATE) == 0) {
//            createPayloadsIfNeeded();
//            mPayloads.add(payload);
//        }
//    }
//
//    private void createPayloadsIfNeeded() {
//        if (mPayloads == null) {
//            mPayloads = new ArrayList<Object>();
//            mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
//        }
//    }
//
//    void clearPayload() {
//        if (mPayloads != null) {
//            mPayloads.clear();
//        }
//        mFlags = mFlags & ~FLAG_ADAPTER_FULLUPDATE;
//    }
//
//    List<Object> getUnmodifiedPayloads() {
//        if ((mFlags & FLAG_ADAPTER_FULLUPDATE) == 0) {
//            if (mPayloads == null || mPayloads.size() == 0) {
//                // Initial state,  no update being called.
//                return FULLUPDATE_PAYLOADS;
//            }
//            // there are none-null payloads
//            return mUnmodifiedPayloads;
//        } else {
//            // a full update has been called.
//            return FULLUPDATE_PAYLOADS;
//        }
//    }
//
//    void resetInternal() {
//        mFlags = 0;
//        mPosition = NO_POSITION;
//        mOldPosition = NO_POSITION;
//        mItemId = NO_ID;
//        mPreLayoutPosition = NO_POSITION;
//        mIsRecyclableCount = 0;
//        mShadowedHolder = null;
//        mShadowingHolder = null;
//        clearPayload();
//        mWasImportantForAccessibilityBeforeHidden = ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO;
//    }
//
//    /**
//     * Called when the child view enters the hidden state
//     */
//    private void onEnteredHiddenState() {
//        // While the view item is in hidden state, make it invisible for the accessibility.
//        mWasImportantForAccessibilityBeforeHidden =
//                ViewCompat.getImportantForAccessibility(itemView);
//        ViewCompat.setImportantForAccessibility(itemView,
//                ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
//    }
//
//    /**
//     * Called when the child view leaves the hidden state
//     */
//    private void onLeftHiddenState() {
//        ViewCompat.setImportantForAccessibility(
//                itemView, mWasImportantForAccessibilityBeforeHidden);
//        mWasImportantForAccessibilityBeforeHidden = ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("ViewHolder{" +
//                Integer.toHexString(hashCode()) + " position=" + mPosition + " id=" + mItemId +
//                ", oldPos=" + mOldPosition + ", pLpos:" + mPreLayoutPosition);
//        if (isScrap()) {
//            sb.append(" scrap ")
//                    .append(mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
//        }
//        if (isInvalid()) sb.append(" invalid");
//        if (!isBound()) sb.append(" unbound");
//        if (needsUpdate()) sb.append(" update");
//        if (isRemoved()) sb.append(" removed");
//        if (shouldIgnore()) sb.append(" ignored");
//        if (isTmpDetached()) sb.append(" tmpDetached");
//        if (!isRecyclable()) sb.append(" not recyclable(" + mIsRecyclableCount + ")");
//        if (isAdapterPositionUnknown()) sb.append(" undefined adapter position");
//
//        if (itemView.getParent() == null) sb.append(" no parent");
//        sb.append("}");
//        return sb.toString();
//    }
//
//    /**
//     * Informs the recycler whether this item can be recycled. Views which are not
//     * recyclable will not be reused for other items until setIsRecyclable() is
//     * later set to true. Calls to setIsRecyclable() should always be paired (one
//     * call to setIsRecyclabe(false) should always be matched with a later call to
//     * setIsRecyclable(true)). Pairs of calls may be nested, as the state is internally
//     * reference-counted.
//     *
//     * @param recyclable Whether this item is available to be recycled. Default value
//     * is true.
//     */
//    public final void setIsRecyclable(boolean recyclable) {
//        mIsRecyclableCount = recyclable ? mIsRecyclableCount - 1 : mIsRecyclableCount + 1;
//        if (mIsRecyclableCount < 0) {
//            mIsRecyclableCount = 0;
//            if (DEBUG) {
//                throw new RuntimeException("isRecyclable decremented below 0: " +
//                        "unmatched pair of setIsRecyable() calls for " + this);
//            }
//            Log.e(VIEW_LOG_TAG, "isRecyclable decremented below 0: " +
//                    "unmatched pair of setIsRecyable() calls for " + this);
//        } else if (!recyclable && mIsRecyclableCount == 1) {
//            mFlags |= FLAG_NOT_RECYCLABLE;
//        } else if (recyclable && mIsRecyclableCount == 0) {
//            mFlags &= ~FLAG_NOT_RECYCLABLE;
//        }
//        if (DEBUG) {
//            Log.d(TAG, "setIsRecyclable val:" + recyclable + ":" + this);
//        }
//    }
//
//    /**
//     * @see {@link #setIsRecyclable(boolean)}
//     *
//     * @return true if this item is available to be recycled, false otherwise.
//     */
//    public final boolean isRecyclable() {
//        return (mFlags & FLAG_NOT_RECYCLABLE) == 0 &&
//                !ViewCompat.hasTransientState(itemView);
//    }
//
//    /**
//     * Returns whether we have animations referring to this view holder or not.
//     * This is similar to isRecyclable flag but does not check transient state.
//     */
//    private boolean shouldBeKeptAsChild() {
//        return (mFlags & FLAG_NOT_RECYCLABLE) != 0;
//    }
//
//    /**
//     * @return True if ViewHolder is not refenrenced by RecyclerView animations but has
//     * transient state which will prevent it from being recycled.
//     */
//    private boolean doesTransientStatePreventRecycling() {
//        return (mFlags & FLAG_NOT_RECYCLABLE) == 0 && ViewCompat.hasTransientState(itemView);
//    }
//
//    boolean isUpdated() {
//        return (mFlags & FLAG_UPDATE) != 0;
//    }
//}

    // 代理模式 通过构造方法注入一个adapter
public class RecyclerViewPagerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final RecyclerViewPager mViewPager;
    RecyclerView.Adapter<VH> mAdapter;


    public RecyclerViewPagerAdapter(RecyclerViewPager viewPager, RecyclerView.Adapter<VH> adapter) {
        mAdapter = adapter;
        mViewPager = viewPager;
        setHasStableIds(mAdapter.hasStableIds());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        mAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        mAdapter.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mAdapter.onBindViewHolder(holder, position);
        final View itemView = holder.itemView;
        ViewGroup.LayoutParams lp = itemView.getLayoutParams() == null ? new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) : itemView.getLayoutParams();
        if (mViewPager.getLayoutManager().canScrollHorizontally()) {
            lp.width = mViewPager.getWidth() - mViewPager.getPaddingLeft() - mViewPager.getPaddingRight();
        } else {
            lp.height = mViewPager.getHeight() - mViewPager.getPaddingTop() - mViewPager.getPaddingBottom();
        }
        itemView.setLayoutParams(lp);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        mAdapter.setHasStableIds(hasStableIds);
    }


    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }


    @Override
    public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }
}
