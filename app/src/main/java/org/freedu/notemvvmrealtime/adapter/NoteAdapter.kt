package org.freedu.notemvvmrealtime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.freedu.notemvvmrealtime.databinding.NoteItemBinding
import org.freedu.notemvvmrealtime.model.Note

class NoteAdapter(
    private val notes:MutableList<Note>,
    private val onEdit:(Note)->Unit,
    private val onDelete:(String)->Unit
):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(private val binding: NoteItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note){
            binding.noteTxt.text = note.title
            binding.contentTxt.text = note.content
            binding.editBtn.setOnClickListener { onEdit(note) }
            binding.deleteBtn.setOnClickListener { onDelete(note.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        return NoteViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateNotes(newNote:List<Note>){
        notes.clear()
        notes.addAll(newNote)
        notifyDataSetChanged()
    }
}